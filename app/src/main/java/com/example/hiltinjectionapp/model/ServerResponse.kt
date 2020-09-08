package com.example.hiltinjectionapp.model

import com.google.gson.JsonElement

data class ServerResponse(
    val status: String,
    val message: String,
    val response: JsonElement?
)