package com.lemzeeyyy.notebookapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lemzeeyyy.notebookapplication.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note?)

    @Query("DELETE FROM notebook_table")
    suspend fun deleteAll();

    @Delete
    suspend fun deleteNote(note: Note?);

    @Update
    suspend fun updateNote(note: Note?);

    @Query("SELECT * FROM notebook_table ORDER BY note_id ASC")
     fun getAllNotes(): Flow<List<Note?>>

    @Query("SELECT * FROM notebook_table WHERE notebook_table.note_id==:id")
     fun getNote(id: Long): Flow<Note?>
}