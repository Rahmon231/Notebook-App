package com.lemzeeyyy.notebookapplication.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lemzeeyyy.notebookapplication.MainActivity
import com.lemzeeyyy.notebookapplication.R
import com.lemzeeyyy.notebookapplication.utils.NoteApplication
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModel
import com.lemzeeyyy.notebookapplication.viewmodel.NoteViewModelFactory
import java.sql.Timestamp


class UpdateNoteFragment : BottomSheetDialogFragment() {

     var bundle: Bundle? = arguments

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        lateinit var  noteViewModel: NoteViewModel
        var noteId : Long = 0
        val newNoteTitle : EditText = view.findViewById(R.id.edit_note_title_update)
        val newDesc  : EditText = view.findViewById(R.id.note_descrip_update)
        val updateBtn : Button = view.findViewById(R.id.update_button)
        val viewModelProvider = NoteViewModelFactory((requireActivity().application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this,viewModelProvider)[NoteViewModel::class.java]


        if (bundle!=null) {
            Log.d("CheckBund", "onViewCreated: $bundle")
            noteId = bundle!!.getLong(MainActivity.NOTE_ID)
            noteViewModel.getNote(noteId).observe(this, Observer {
                if (it != null) {
                    newNoteTitle.setText(it.noteTitle)
                    newDesc.setText(it.noteDescription)
                }
            })
        }



            updateBtn.setOnClickListener {
            val newNoteTitleString : String = newNoteTitle.text.toString()
            val newDescString : String = newDesc.text.toString()
            val timestamp = Timestamp(System.currentTimeMillis())

                mainActivity.getUpdateData(newNoteTitleString,newDescString,timestamp,noteId)
                fragmentManager?.popBackStack()

            dismiss()

        }


    }


    override fun onDetach() {
        super.onDetach()
        bundle = null

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bundle = arguments
    }

}