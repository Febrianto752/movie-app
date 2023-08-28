package com.example.movieapp

import android.app.Application
import com.example.movieapp.data.database.AppContainer
import com.example.movieapp.data.database.AppDataContainer


class MovieApplication: Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
