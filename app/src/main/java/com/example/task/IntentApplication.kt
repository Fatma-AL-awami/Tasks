package com.example.task

import android.app.Application

class IntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    TaskRepository.initialize(this)
    }
}
