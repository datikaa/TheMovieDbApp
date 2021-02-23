package com.datikaa.themoviedbapp.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpcomingResponse(
    val dates: Dates?,
    val page: Int?,
    val results: List<UpcomingMovie> = emptyList(),
    val total_pages: Int?,
    val total_results: Int?
)