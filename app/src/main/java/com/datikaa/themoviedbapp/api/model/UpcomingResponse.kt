package com.datikaa.themoviedbapp.api.model

data class UpcomingResponse(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)