package com.example.hiltinjectionapp.di

import android.content.Context
import androidx.room.Room
import com.example.hiltinjectionapp.constants.Constants.Companion.DB_NAME
import com.example.hiltinjectionapp.datasource.LocalDataSource
import com.example.hiltinjectionapp.datasource.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideNoteDao(noteDb: LocalDataSource):NoteDao{
        return noteDb.noteDao()
    }

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext appContext: Context):LocalDataSource{
        return Room.databaseBuilder(appContext, LocalDataSource::class.java, DB_NAME)
            .build()
    }
}