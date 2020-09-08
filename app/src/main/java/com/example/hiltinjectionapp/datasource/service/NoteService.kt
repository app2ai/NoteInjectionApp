package com.example.hiltinjectionapp.datasource.service

import com.example.hiltinjectionapp.model.Notes

// Aim of this class to provide UI level schema where DOA design for DB level schema
interface NoteService{
    fun getAllNotes(callback: (List<Notes>) -> Unit)
    fun addNote(notes: Notes, callback: (Long) -> Unit)
    fun delNote(id: Int)
    fun singleNote(id: Int, callback: (Notes) -> Unit)
}