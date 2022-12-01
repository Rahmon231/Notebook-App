package com.lemzeeyyy.notebookapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lemzeeyyy.notebookapplication.MainActivity
import com.lemzeeyyy.notebookapplication.R
import com.lemzeeyyy.notebookapplication.utils.NoteApplication
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModel
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModelFactory
import java.sql.Timestamp

class AddNewNoteFragment : BottomSheetDialogFragment() {

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, callback)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        lateinit var  noteViewModel: NoteViewModel
        var noteId : Long = 0
        var isUpdate:Boolean = false
        val newNoteTitle : EditText = view.findViewById(R.id.edit_note_title)
        val newDesc  : EditText = view.findViewById(R.id.note_descrip)
        val saveBtn : Button = view.findViewById(R.id.save_button)
        val viewModelProvider = NoteViewModelFactory((requireActivity().application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this,viewModelProvider)[NoteViewModel::class.java]

        saveBtn.setOnClickListener {
            val newNoteTitleString : String = newNoteTitle.text.toString()
            val newDescString : String = newDesc.text.toString()
            val timestamp = Timestamp(System.currentTimeMillis())
            mainActivity.getNewNoteData(newNoteTitleString,newDescString,timestamp)
            dismiss()
        }

    }


    }
