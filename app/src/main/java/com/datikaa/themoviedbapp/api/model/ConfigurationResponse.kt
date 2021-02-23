package com.datikaa.themoviedbapp.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationResponse(
    val change_keys: List<String>,
    val images: Images
)