package com.charlye934.dogstest.core.network

import android.content.Context
import com.charlye934.dogstest.R

open class BaseApiResponse {
    suspend fun <T> safeApiCall(
        context: Context,
        apiCall: suspend () -> BaseServiceResponse<T>
    ): Resources<T> {
        return try {
            val response = apiCall()
            if(response.success && response.result != null)
                Resources.Success(response.result)
            else
                Resources.Error(
                    error = BaseError(
                        message = response.error?.message ?: context.getString(R.string.generic_subtitle_error),
                        code = response.error?.code ?: "-1"
                    )
                )
        }catch (e: Exception){
            Resources.Error(
                error = BaseError(
                    message = context.getString(R.string.generic_subtitle_error),
                    code = "400"
                )
            )
        }
    }
}