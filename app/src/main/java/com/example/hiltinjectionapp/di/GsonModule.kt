package com.example.hiltinjectionapp.di

import com.example.hiltinjectionapp.annotation.GsonLenient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class GsonModule {

    @GsonLenient
    @Provides
    fun provideGson():Gson{
        return GsonBuilder()
            .setLenient()
            .create()
    }
}