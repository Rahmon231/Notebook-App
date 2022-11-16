package com.lemzeeyyy.notebookapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lemzeeyyy.notebookapplication.R
import com.lemzeeyyy.notebookapplication.model.Note

class NoteAdapter(private var context: Context, private var notes: List<Note?>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteTitle : TextView = itemView.findViewById(R.id.row_note_title)
        var timeStamp : TextView = itemView.findViewById(R.id.row_time_stamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val view:View = LayoutInflater.from(context).inflate(R.layout.note_row,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: Note? = notes[position]
        holder.noteTitle.setText(note?.noteTitle)
        holder.timeStamp.setText("Last Updated: ${note?.getTimeStamp()}")
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}