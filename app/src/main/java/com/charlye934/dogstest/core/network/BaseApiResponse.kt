package com.charlye934.dogstest.core.network

import android.content.Context
import com.charlye934.dogstest.R
import retrofit2.Response

open class BaseApiResponse {
    suspend fun <T : Any> safeApiCall(
        context: Context,
        apiCall: suspend () -> Response<T>
    ): TaskUiState<T> {
        return try {
            val result = apiCall()
            if (result.isSuccessful) {
                TaskUiState.Success(result.body() as T)
            } else {
                val exception = result.errorBody()
                    ?: Exception(context.getString(R.string.generic_subtitle_error))
                TaskUiState.Error(
                    error = BaseError(
                        message = result.message()
                            ?: context.getString(R.string.generic_subtitle_error),
                        code = "UNKNOWN_ERROR"
                    )
                )
            }
        } catch (e: Exception) {
            TaskUiState.Error(
                error = BaseError(
                    message = context.getString(R.string.generic_subtitle_error),
                    code = "400"
                )
            )
        }
    }
}