package com.example.hiltinjectionapp.di

import com.example.hiltinjectionapp.datasource.Repository
import com.example.hiltinjectionapp.datasource.service.NoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/*
@Qualifier
annotation class NoteDatabase

@InstallIn(ApplicationComponent::class)
@Module
abstract class ServiceRepoModule {

    @NoteDatabase
    @Singleton
    @Binds
    abstract fun bindDbNote(impl: Repository):NoteService
}*/
