package com.datikaa.themoviedbapp.base

import com.datikaa.themoviedbapp.api.service.Result
import java.io.IOException
import kotlin.Exception

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> T,
        onError: suspend (Exception) -> Unit
    ): T? {

        val result: Result<T> = safeApiResult(call)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                onError.invoke(result.exception)
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> T): Result<T> {
        val response = call.invoke()
        return try {
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(IOException("Error Occurred during getting safe Api result"))
        }
    }
}