package com.lemzeeyyy.notebookapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Timestamp

@Entity(tableName = "notebook_table")
class Note {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Long = 0

    @ColumnInfo(name = "note_title")
    var noteTitle: String? = null

    @ColumnInfo(name = "note_description")
    var noteDescription: String? = null

    @ColumnInfo(name = "time_stamp")
    @TypeConverters(TimeConverter::class)
    var timestamp: Timestamp? = null

    constructor(noteTitle: String?, noteDescription: String?, timestamp: Timestamp?) {
        this.noteTitle = noteTitle
        this.noteDescription = noteDescription
        this.timestamp = timestamp
    }


}