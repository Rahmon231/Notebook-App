package com.lemzeeyyy.notebookapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lemzeeyyy.notebookapplication.utils.TimeConverter
import java.sql.Timestamp

@Entity(tableName = "notebook_table")
class Note(
    @ColumnInfo(name = "note_title")
    var noteTitle: String?,

    @ColumnInfo(name = "note_description")
    var noteDescription: String?,

    @ColumnInfo(name = "time_stamp")
    @TypeConverters(TimeConverter::class)
    var timestamp: Timestamp?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Long = 0

    fun getTimeStamp() : Timestamp? {
        return timestamp
    }
}