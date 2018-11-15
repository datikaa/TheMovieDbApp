package com.datikaa.themoviedbapp.api.interceptors

import com.datikaa.themoviedbapp.api.ApiKey
import okhttp3.Interceptor
import okhttp3.Response



class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", ApiKey) // TODO - maybe an API key should be somewhere else?
            .build()


        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}