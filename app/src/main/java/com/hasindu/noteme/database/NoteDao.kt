package com.hasindu.noteme.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hasindu.noteme.model.Note

@Dao
interface NoteDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY  id DESC")
    fun getAllNotes():LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE nTitle LIKE:query OR nDescription LIKE:query ")
    fun  searchNote(query: String?):LiveData<List<Note>>
}