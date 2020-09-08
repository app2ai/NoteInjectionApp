package com.example.hiltinjectionapp.datasource.service

import com.example.hiltinjectionapp.model.Notes

interface NoteApiService{
    fun addApiNote(notes: Notes, callback: (Int) -> Unit)
}