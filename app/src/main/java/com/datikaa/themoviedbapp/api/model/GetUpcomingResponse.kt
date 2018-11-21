package com.datikaa.themoviedbapp.api.model

data class GetUpcomingResponse(
    val dates: Dates?,
    val page: Int?,
    val results: List<UpcomingMovie>?,
    val total_pages: Int?,
    val total_results: Int?
)