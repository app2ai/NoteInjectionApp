package com.example.hiltinjectionapp.datasource.dao

import androidx.annotation.WorkerThread
import androidx.room.*
import com.example.hiltinjectionapp.model.Notes

@Dao
interface NoteDao {

    @WorkerThread
    @Query("SELECT * FROM tblNotes")
    fun allNotes():MutableList<Notes>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: Notes):Long

    @WorkerThread
    @Query("DELETE FROM tblNotes WHERE note_id = :noteId")
    fun deleteNote(noteId: Int):Int

    @WorkerThread
    @Query("SELECT * FROM tblNotes WHERE note_id = :id")
    fun noteById(id: Int):Notes
}