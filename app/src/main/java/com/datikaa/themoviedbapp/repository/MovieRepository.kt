package com.datikaa.themoviedbapp.repository

import android.util.Log
import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.base.BaseRepository

class MovieRepository constructor(
    private val tmdbApi: TheMovieDbApi,
) : BaseRepository() {

    suspend fun getUpcomingMovies(): MutableList<UpcomingMovie>? {
        //safeApiCall is defined in BaseRepository.kt
        val movieResponse = safeApiCall(
            call = { tmdbApi.endpoints.getLatestAsync() },
            onError = { Log.d("ApiError", "${it.message}") }
        )
        return movieResponse?.results?.toMutableList()
    }

    suspend fun getMovie(id: String): Movie? {
        //safeApiCall is defined in BaseRepository.kt
        return safeApiCall(
            call = { tmdbApi.endpoints.getMovieAsync(id) },
            onError = { Log.d("ApiError", "${it.message}") }
        )
    }
}