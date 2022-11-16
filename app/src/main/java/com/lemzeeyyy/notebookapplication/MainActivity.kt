package com.lemzeeyyy.notebookapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lemzeeyyy.notebookapplication.adapter.NoteAdapter
import com.lemzeeyyy.notebookapplication.model.Note
import com.lemzeeyyy.notebookapplication.repository.NoteRepository
import com.lemzeeyyy.notebookapplication.utils.NoteApplication
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModel
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var  noteViewModel:NoteViewModel
    lateinit var noteRecyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter
    lateinit var floatingActionButton: FloatingActionButton
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
        noteViewModel.myAllNotes.observe(this, Observer {notes->
            setupRecyclerView(notes)

        })

        floatingActionButton.setOnClickListener {
            addNewNote()
        }

    }

    private fun addNewNote() {
        val dialog = AddNewNoteFragment()

        val view = layoutInflater.inflate(R.layout.fragment_add_new_note, null)
        dialog.setCancelable(true)
        dialog.show(supportFragmentManager,"AddNewNote")
    }

    private fun setupRecyclerView(notes : List<Note?>){
        noteRecyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        noteAdapter = NoteAdapter(this@MainActivity,notes)
        noteRecyclerView.adapter = noteAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete_all,menu)
        return super.onCreateOptionsMenu(menu)
    }
}