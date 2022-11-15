package com.lemzeeyyy.notebookapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lemzeeyyy.notebookapplication.model.Note
import com.lemzeeyyy.notebookapplication.repository.NoteRepository
import com.lemzeeyyy.notebookapplication.utils.NoteApplication
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModel
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var  noteViewModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        val viewModelProvider = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel = ViewModelProvider(this,viewModelProvider)[NoteViewModel::class.java]



        noteViewModel.myAllNotes.observe(this, Observer {

           Log.d("Tagu", "onCreate: ${it[0]?.noteTitle}")
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete_all,menu)
        return super.onCreateOptionsMenu(menu)
    }
}