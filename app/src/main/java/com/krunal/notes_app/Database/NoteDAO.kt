package com.krunal.notes_app.Database

import androidx.room.*
import com.krunal.notes_app.Model.NotesModel

@Dao
interface NoteDAO {

    @Insert
     fun addNote(note: NotesModel)

    @Query("SELECT * FROM notesmodel ORDER BY id DESC")
     fun getAllNotes(): List<NotesModel>

    @Query("SELECT * FROM notesmodel LIMIT 5 OFFSET :page")
     fun getNotesPaginations(page: Int): List<NotesModel>

    @Insert
     fun addMultipleNotes(note: NotesModel)

    @Update
     fun updateNote(note: NotesModel)

    @Delete
     fun deleteNote(note: NotesModel)

}