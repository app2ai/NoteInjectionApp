package com.example.hiltinjectionapp.datasource.rest_api

import com.example.hiltinjectionapp.model.Notes
import com.example.hiltinjectionapp.model.ServerResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiListener {

    @POST("add-note")
    fun addNote(@Body note: Notes):Call<ServerResponse>
}