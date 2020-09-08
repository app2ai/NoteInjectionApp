package com.example.hiltinjectionapp.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiltinjectionapp.datasource.dao.NoteDao
import com.example.hiltinjectionapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class LocalDataSource : RoomDatabase() {
  abstract fun noteDao():NoteDao
}