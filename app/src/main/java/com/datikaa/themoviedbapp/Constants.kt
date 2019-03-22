package com.datikaa.themoviedbapp

const val PicassoBaseUrl: String = "https://image.tmdb.org/t/p"
const val PicSizeW500: String = "/w500"
val tmdbApiKey: String
    get() = BuildConfig.TMDB_API_KEY