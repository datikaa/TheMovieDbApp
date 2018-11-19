package com.datikaa.themoviedbapp

import android.app.Application
import com.datikaa.themoviedbapp.api.interceptors.ApiKeyInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class TheMovieDbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpPicasso()
    }

    fun setUpPicasso() {

        val okHttpLoggingInterceptor =
            HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE)

        val client = OkHttpClient.Builder()
            .addInterceptor(okHttpLoggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val picasso = Picasso.Builder(applicationContext)
            .downloader(OkHttp3Downloader(client))
            .build()
        Picasso.setSingletonInstance(picasso)
    }
}