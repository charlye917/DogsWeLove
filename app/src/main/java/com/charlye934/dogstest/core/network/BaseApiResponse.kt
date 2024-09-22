package com.charlye934.dogstest.core.network

import android.content.Context
import retrofit2.Response

open class BaseApiResponse {
    suspend fun <T : Any> safeApiCall(
        context: Context,
        apiCall: suspend () -> Response<T>
    ): TaskUiState<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                TaskUiState.Success(response.body()!!)
            } else {
                TaskUiState.Error(
                    BaseError(
                        message = response.message() ?: "Unknown error",
                        code = response.code().toString()
                    )
                )
            }
        } catch (e: Exception) {
            TaskUiState.Error(
                BaseError(
                    message = e.localizedMessage ?: "Unknown error",
                    code = "NETWORK_ERROR"
                )
            )
        }
    }
}