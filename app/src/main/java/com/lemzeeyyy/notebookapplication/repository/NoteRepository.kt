package com.lemzeeyyy.notebookapplication.repository

import androidx.annotation.WorkerThread
import com.lemzeeyyy.notebookapplication.model.Note
import com.lemzeeyyy.notebookapplication.room.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    private val allNotes : Flow<List<Note?>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    @WorkerThread
    suspend fun deleteAll(){
        noteDao.deleteAll()
    }

    @WorkerThread
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    @WorkerThread
    suspend fun updateNote(note:Note){
        noteDao.updateNote(note)
    }

    @JvmName("getAllNotes1")
    @WorkerThread
     fun getAllNotes() : Flow<List<Note?>>{
        return allNotes
    }

    fun getNote(id:Long) : Flow<Note?> {
        return noteDao.getNote(id)
    }

}