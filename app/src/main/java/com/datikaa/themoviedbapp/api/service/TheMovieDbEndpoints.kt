package com.datikaa.themoviedbapp.api.service

import com.datikaa.themoviedbapp.api.model.ConfigurationResponse
import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.api.model.UpcomingResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbEndpoints {

    @GET("movie/{movie_id}")
    suspend fun getMovieAsync(@Path("movie_id") id: String): Movie

    @GET("movie/upcoming")
    suspend fun getLatestAsync(): UpcomingResponse

    @GET("configuration")
    suspend fun getConfigurationAsync(): ConfigurationResponse
}