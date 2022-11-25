package com.lemzeeyyy.notebookapplication.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.lemzeeyyy.notebookapplication.MainActivity.Companion.NOTE_ID
import com.lemzeeyyy.notebookapplication.R
import com.lemzeeyyy.notebookapplication.UpdateNoteFragment
import com.lemzeeyyy.notebookapplication.clickhandlers.ClickHandler
import com.lemzeeyyy.notebookapplication.model.Note

class NoteAdapter(private var context: Context, private var notes: List<Note?>,  clickHandler: ClickHandler) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
     var myNote: Note? = null
     var  clickHandler:ClickHandler = clickHandler


//    val viewModelProvider = NoteViewModelFactory((context.applicationContext as NoteApplication).repository)
//
//    var viewModel : NoteViewModel = ViewModelProvider(this.context,viewModelProvider)[NoteViewModel::class.java]


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteTitle : TextView = itemView.findViewById(R.id.row_note_title)
        var timeStamp : TextView = itemView.findViewById(R.id.row_time_stamp)

        val deleteBtn : ImageView = itemView.findViewById(R.id.delete_btn)
        val shareBtn : ImageView = itemView.findViewById(R.id.share_btn)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val view:View = LayoutInflater.from(context).inflate(R.layout.note_row,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
         myNote = notes[position]
        holder.noteTitle.setText(myNote?.noteTitle)
        holder.timeStamp.setText("Last Updated: ${myNote?.getTimeStamp()}")



        holder.noteTitle.setOnClickListener {
            val noteId : Long = notes[position]!!.getId()
            val bundle = Bundle()
            bundle.putLong(NOTE_ID,noteId)
            var updateDialog: UpdateNoteFragment = UpdateNoteFragment()
            updateDialog.arguments = bundle
            updateDialog.setCancelable(false)
          // val fragmentManager:FragmentManager = ((context) as FragmentActivity).supportFragmentManager
            updateDialog.show(((context) as FragmentActivity).supportFragmentManager,"UpdateNote")




           // updateDialog.show("","UpdateNote")


           clickHandler.updateHandler(notes[position],noteId)

        }
        holder.deleteBtn.setOnClickListener {
            clickHandler.deleteHandler(notes[position],position)

        }
        holder.shareBtn.setOnClickListener {
            clickHandler.shareHandler(notes[position],position)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    fun setNote(myNote : Note?){
        this.myNote = myNote
       // notifyDataSetChanged()
    }
    fun setNotes(notes: List<Note?>){
        this.notes = notes
        notifyDataSetChanged()
    }


}