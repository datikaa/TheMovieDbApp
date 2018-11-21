package com.datikaa.themoviedbapp.api.service

import com.datikaa.themoviedbapp.api.model.GetConfigurationResponse
import com.datikaa.themoviedbapp.api.model.GetMovieResponse
import com.datikaa.themoviedbapp.api.model.GetUpcomingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbService {

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") id: String): Observable<GetMovieResponse>

    @GET("movie/upcoming")
    fun getLatest(): Observable<GetUpcomingResponse>

    @GET("configuration")
    fun getConfiguration(): Observable<GetConfigurationResponse>
}