package com.lemzeeyyy.notebookapplication.viewmodel

import androidx.lifecycle.*
import com.lemzeeyyy.notebookapplication.model.Note
import com.lemzeeyyy.notebookapplication.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository): ViewModel() {
    val myAllNotes : LiveData<List<Note?>> = noteRepository.getAllNotes().asLiveData()


    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.insertNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(note)
    }
    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteAll()
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.updateNote(note)
    }

    fun getNote(id: Long) : LiveData<Note?>{
        return noteRepository.getNote(id).asLiveData()
    }

    fun getAllNotes() = viewModelScope.launch(Dispatchers.IO){
        myAllNotes
    }



}

class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(noteRepository) as T
        }else{
            throw IllegalArgumentException("unknown viewmodel")
        }
    }
}