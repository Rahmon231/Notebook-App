package com.lemzeeyyy.notebookapplication.clickhandlers

import com.lemzeeyyy.notebookapplication.model.Note

interface ClickHandler {
    fun deleteHandler(note: Note?, position:Int)
    fun shareHandler(note: Note?, position:Int)
    fun updateHandler(note: Note?, noteId:Long)

}