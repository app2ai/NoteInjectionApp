package com.example.hiltinjectionapp.di

//import com.example.hiltinjectionapp.annotation.BasicAuthInterceptorOkHttpClient
import com.example.hiltinjectionapp.annotation.GsonLenient
import com.example.hiltinjectionapp.constants.Constants.Companion.BASE_URL
import com.example.hiltinjectionapp.datasource.rest_api.ApiListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideApiService(
        /*@BasicAuthInterceptorOkHttpClient */okHttpClient: OkHttpClient,
        @GsonLenient gson: Gson
    ):ApiListener{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ApiListener::class.java)
    }
}