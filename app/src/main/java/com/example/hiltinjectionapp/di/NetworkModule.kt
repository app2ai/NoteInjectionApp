package com.example.hiltinjectionapp.di

import com.example.hiltinjectionapp.annotation.BasicAuthInterceptorOkHttpClient
import com.example.hiltinjectionapp.datasource.interceptor.BasicAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    //@BasicAuthInterceptorOkHttpClient
    @Provides
    fun provideBasicAuthInterceptorOkHttpClient(
        //basicAuthInterceptor: BasicAuthInterceptor
    ):OkHttpClient{
        return OkHttpClient.Builder()
            //.addInterceptor(basicAuthInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectionSpecs(mutableListOf<ConnectionSpec>(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
            .build()
    }
}