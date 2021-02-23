package com.datikaa.themoviedbapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieDbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}