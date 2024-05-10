package com.hasindu.noteme.repository

import androidx.room.Query
import com.hasindu.noteme.database.NoteDatabase
import com.hasindu.noteme.model.Note

class NoteRepository(private val db: NoteDatabase) {

    // Insert a note
    suspend fun insertNote(note: Note) =
        db.getNoteDao().insertNote(note)


    // Delete a note
    suspend fun deleteNote(note: Note) =
        db.getNoteDao().deleteNote(note)


    // Update a note
    suspend fun updateNote(note: Note) =
        db.getNoteDao().updateNote(note)


    // Get all notes
    fun getAllNotes() = db.getNoteDao().getAllNotes()

    // Search for notes
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
}
//like a container -repo

// suspend eken mokakda karanne, meken karnne background therad eken run wenwda balana eka