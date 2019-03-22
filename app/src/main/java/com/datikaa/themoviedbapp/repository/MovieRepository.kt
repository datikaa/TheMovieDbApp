package com.datikaa.themoviedbapp.repository

import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.api.service.TheMovieDbService
import com.datikaa.themoviedbapp.base.BaseRepository

class MovieRepository : BaseRepository() {

    private val api: TheMovieDbService = TheMovieDbApi.theMovieDbService

    suspend fun getPopularMovies(): MutableList<UpcomingMovie>? {
        //safeApiCall is defined in BaseRepository.kt
        val movieResponse = safeApiCall(
            call = { api.getLatest().await() },
            errorMessage = "Error Fetching Popular Movies"
        )
        return movieResponse?.results?.toMutableList()
    }

    suspend fun getMovie(id: String): Movie? {
        //safeApiCall is defined in BaseRepository.kt
        return safeApiCall(
            call = { api.getMovie(id).await() },
            errorMessage = "Error Fetching Popular Movies"
        )
    }
}