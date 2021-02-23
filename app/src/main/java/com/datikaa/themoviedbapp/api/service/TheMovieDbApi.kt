package com.datikaa.themoviedbapp.api.service

import com.datikaa.themoviedbapp.BuildConfig
import com.datikaa.themoviedbapp.api.interceptors.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TheMovieDbApi {

    val theMovieDbService: TheMovieDbService

    init{
        val okHttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE)

        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(okHttpLoggingInterceptor)
                .addInterceptor(ApiKeyInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        theMovieDbService = retrofit.create(TheMovieDbService::class.java)
    }
}