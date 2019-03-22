package com.datikaa.themoviedbapp.repository

import android.util.Log
import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.api.service.TheMovieDbService
import com.datikaa.themoviedbapp.base.BaseRepository

class MovieRepository : BaseRepository() {

    private val api: TheMovieDbService = TheMovieDbApi.theMovieDbService

    suspend fun getUpcomingMovies(): MutableList<UpcomingMovie>? {
        //safeApiCall is defined in BaseRepository.kt
        val movieResponse = safeApiCall(
            call = { api.getLatest().await() },
            onError = { Log.d("ApiError", "${it.message}") }
        )
        return movieResponse?.results?.toMutableList()
    }

    suspend fun getMovie(id: String): Movie? {
        //safeApiCall is defined in BaseRepository.kt
        return safeApiCall(
            call = { api.getMovie(id).await() },
            onError = { Log.d("ApiError", "${it.message}") }
        )
    }
}