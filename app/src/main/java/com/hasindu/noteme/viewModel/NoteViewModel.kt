package com.hasindu.noteme.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.hasindu.noteme.model.Note
import com.hasindu.noteme.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app:Application,private val  noteRepository:NoteRepository):AndroidViewModel(app) {



    fun addNote(note:Note) =viewModelScope.launch {
        noteRepository. insertNote(note)
    }

    fun updateNote(note:Note) =viewModelScope.launch {
        noteRepository. updateNote(note)
    }

    fun deleteNote(note:Note) =viewModelScope.launch {
        noteRepository. deleteNote(note)
    }


    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNotes(query: String?) = noteRepository.searchNote(query)
}

