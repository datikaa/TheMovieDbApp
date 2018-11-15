package com.datikaa.themoviedbapp.service

import retrofit2.http.GET

interface TheMovieDbService {

    @GET()
    fun searchMovie()

}