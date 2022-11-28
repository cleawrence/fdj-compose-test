package com.appiwedia.apps.android.fdjcompose.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T,
    ): Resource<T> {
        return withContext(dispatcher) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Resource.Error("IO Exception: ${throwable.message}")
                    is HttpException -> Resource.Error("Http Exception: ${throwable.message}")
                    is TimeoutException -> Resource.Error("Timeout Exception: ${throwable.message}")
                    else -> error(throwable)
                }
            }
        }
    }

    private fun <T> error(errorMessage: Throwable): Resource<T> =
        Resource.Error(errorMessage.message ?: "Une erreur inattendue est survenue")
}