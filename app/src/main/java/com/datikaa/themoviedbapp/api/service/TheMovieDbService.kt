package com.datikaa.themoviedbapp.api.service

import com.datikaa.themoviedbapp.api.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbService {

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") id: String): Call<Movie>

}