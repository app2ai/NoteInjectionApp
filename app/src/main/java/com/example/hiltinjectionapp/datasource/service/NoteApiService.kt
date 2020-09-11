package com.example.hiltinjectionapp.datasource.service

import com.example.hiltinjectionapp.model.LocalMessages
import com.example.hiltinjectionapp.model.Notes

interface NoteApiService{
    fun addApiNote(notes: Notes, callback: (LocalMessages) -> Unit)
    //fun allApiNotes(callback: (List<Notes>) -> Unit)
    fun syncApiNotes(callback: (LocalMessages) -> Unit)
}