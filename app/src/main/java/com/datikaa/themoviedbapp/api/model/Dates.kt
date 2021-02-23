package com.datikaa.themoviedbapp.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dates(
    val maximum: String?,
    val minimum: String?
)