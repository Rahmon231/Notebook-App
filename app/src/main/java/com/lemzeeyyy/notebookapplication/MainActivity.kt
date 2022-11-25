package com.lemzeeyyy.notebookapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lemzeeyyy.notebookapplication.adapter.NoteAdapter
import com.lemzeeyyy.notebookapplication.clickhandlers.ClickHandler
import com.lemzeeyyy.notebookapplication.model.Note
import com.lemzeeyyy.notebookapplication.utils.NoteApplication
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModel
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModelFactory
import java.sql.Timestamp

class MainActivity : AppCompatActivity(), ClickHandler {
    lateinit var toolbar: Toolbar
    lateinit var  noteViewModel:NoteViewModel
    lateinit var noteRecyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var  bundle:Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRecyclerView = findViewById(R.id.recycler_view)
        floatingActionButton = findViewById(R.id.fab)

        var notes:List<Note?> = emptyList()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val viewModelProvider = NoteViewModelFactory((application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this,viewModelProvider)[NoteViewModel::class.java]
       // noteViewModel.deleteAllNotes()
        noteViewModel.myAllNotes.observe(this, Observer {notes->
            setupRecyclerView(notes)


        })

        floatingActionButton.setOnClickListener {
            openNewNoteFragment()


        }

    }

    private fun openNewNoteFragment() {
        val dialog:AddNewNoteFragment = AddNewNoteFragment()
        dialog.setCancelable(false)
        dialog.show(supportFragmentManager,"AddNewNote")
    }


    private fun setupRecyclerView(notes : List<Note?>){
        noteRecyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        noteAdapter = NoteAdapter(this@MainActivity,notes,this)
        noteRecyclerView.adapter = noteAdapter
        noteAdapter.setNotes(notes)


    }

    fun getNewNoteData(title : String, description : String, timeStamp : Timestamp){
        val note = Note(title,description,timeStamp)

        noteViewModel.insertNote(note)


    }
    fun getUpdateData(title : String, description : String, timeStamp : Timestamp,id:Long){
        val note = Note(title,description,timeStamp)
        note.setId(id)
        noteViewModel.updateNote(note)


    }

    override fun updateHandler(note: Note?, noteId: Long) {
//       bundle = Bundle()
//        note?.setId(noteId)
//        noteViewModel.getNote(noteId).observe(this@MainActivity, Observer {
//           bundle.putLong(NOTE_ID,noteId)
//            var updateDialog:UpdateNoteFragment = UpdateNoteFragment()
//            updateDialog.arguments = bundle
//            updateDialog.setCancelable(false)
//            updateDialog.show(supportFragmentManager,"UpdateNote")
//
//
//        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete_all,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_settings){
            noteViewModel.deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun deleteHandler(note: Note?, position: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Do you want to delete this note?")
            .setPositiveButton("delete", DialogInterface.OnClickListener { dialog, which ->
                noteViewModel.deleteNote(note!!)
            })
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

            })
            .setCancelable(false)
            .show()
    }

    override fun shareHandler(note: Note?, position: Int) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, note!!.noteTitle)
        intent.putExtra(Intent.EXTRA_TEXT, note.noteDescription)
        startActivity(intent)
    }

    companion object {
        const val NOTE_ID : String = "note_id"
    }


}