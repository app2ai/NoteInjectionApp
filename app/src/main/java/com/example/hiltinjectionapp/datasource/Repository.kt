package com.example.hiltinjectionapp.datasource

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.hiltinjectionapp.constants.Constants.Companion.API_LOG_TAG
import com.example.hiltinjectionapp.constants.Constants.Companion.CLIENT_FAILED_ERROR
import com.example.hiltinjectionapp.constants.Constants.Companion.FAILED_RESPONSE
import com.example.hiltinjectionapp.constants.Constants.Companion.NOT_FOUND
import com.example.hiltinjectionapp.constants.Constants.Companion.SERVER_ERROR
import com.example.hiltinjectionapp.constants.Constants.Companion.SUCCESS
import com.example.hiltinjectionapp.constants.Constants.Companion.SUCCESS_RESPONSE
import com.example.hiltinjectionapp.constants.Constants.Companion.UNKNOWN_ERROR
import com.example.hiltinjectionapp.datasource.dao.NoteDao
import com.example.hiltinjectionapp.datasource.rest_api.ApiListener
import com.example.hiltinjectionapp.datasource.service.NoteApiService
import com.example.hiltinjectionapp.datasource.service.NoteService
import com.example.hiltinjectionapp.model.Notes
import com.example.hiltinjectionapp.model.ServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class Repository @Inject constructor(
    private val noteDao: NoteDao,
    private val apiListener: ApiListener
):NoteService, NoteApiService{

    private var executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getAllNotes(callback: (List<Notes>) -> Unit){
        executorService.execute {
            val list = noteDao.allNotes()
            mainThreadHandler.post{
                callback(list)
            }
        }
    }

    override fun addNote(notes: Notes, callback: (Long) -> Unit){
        executorService.execute {
            val i = noteDao.addNote(note = notes)
            println("Added values $i")
            mainThreadHandler.post{
                callback(i)
            }
        }
    }

    override fun delNote(id: Int){
        executorService.execute {
            noteDao.deleteNote(id)
        }
    }

    override fun singleNote(id: Int, callback: (Notes) -> Unit){
        executorService.execute {
            val note = noteDao.noteById(id = id)
            mainThreadHandler.post{
                callback(note)
            }
        }
    }

    override fun addApiNote(notes: Notes, callback: (Int) -> Unit) {
        executorService.execute {
            apiListener.addNote(note = notes).enqueue(
                object : Callback<ServerResponse>{
                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                        mainThreadHandler.post{
                            println("API failure: ${t.message}")
                            callback(CLIENT_FAILED_ERROR)
                        }
                    }

                    override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                        val serRes = response.body()
                        when(response.code()){
                            in 200..210 -> {
                                Log.i(API_LOG_TAG, serRes?.message?:"No Response Message")
                                if (serRes?.status.equals(SUCCESS)){
                                    mainThreadHandler.post{
                                        callback(SUCCESS_RESPONSE)
                                    }
                                }else{
                                    mainThreadHandler.post{
                                        callback(FAILED_RESPONSE)
                                    }
                                }
                            }
                            in 400..445 ->{
                                mainThreadHandler.post{
                                    callback(NOT_FOUND)
                                }
                            }
                            in 500..515 ->{
                                mainThreadHandler.post{
                                    callback(SERVER_ERROR)
                                }
                            }
                            else ->{
                                mainThreadHandler.post{
                                    callback(UNKNOWN_ERROR)
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}