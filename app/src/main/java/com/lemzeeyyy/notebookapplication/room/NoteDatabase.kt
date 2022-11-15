package com.lemzeeyyy.notebookapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lemzeeyyy.notebookapplication.model.Note
import com.lemzeeyyy.notebookapplication.utils.TimeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@TypeConverters(TimeConverter::class)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao

    companion object{

        private const val NOTE_DATABASE : String = "note_database"
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : NoteDatabase{
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(context.applicationContext,
//                NoteDatabase::class.java,NOTE_DATABASE)
//                    .build()
//                INSTANCE = instance
//                instance
            if(INSTANCE == null) {
                synchronized(NoteDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, NOTE_DATABASE)
                            .addCallback(NoteDatabaseCallback(scope))
                            .build()
                    }
                }
            }
            return INSTANCE as NoteDatabase
        }
    }
    private class NoteDatabaseCallback(private val scope:CoroutineScope) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database->
                scope.launch {
                    val noteDao : NoteDao = database.getNoteDao()
                    noteDao.deleteAll()
                }
            }
        }

    }

}