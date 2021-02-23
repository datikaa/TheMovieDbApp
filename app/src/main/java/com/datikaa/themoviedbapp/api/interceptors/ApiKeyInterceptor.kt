package com.datikaa.themoviedbapp.api.interceptors

import com.datikaa.themoviedbapp.tmdbApiKey
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $tmdbApiKey")
            .build()
        return chain.proceed(request)
    }
}