package com.datikaa.themoviedbapp.api.model

data class GetConfigurationResponse(
    val change_keys: List<String>,
    val images: Images
)