package com.lemzeeyyy.notebookapplication.utils

import android.app.Application
import com.lemzeeyyy.notebookapplication.repository.NoteRepository
import com.lemzeeyyy.notebookapplication.room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}