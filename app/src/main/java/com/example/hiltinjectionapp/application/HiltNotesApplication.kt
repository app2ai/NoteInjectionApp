package com.example.hiltinjectionapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltNotesApplication : Application(){}