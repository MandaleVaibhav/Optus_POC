package com.example.optuspoc.applicationContext

import android.app.Application
import android.content.Context

/**
 * This class is for defining application context
 */
class ApplicationContext : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}