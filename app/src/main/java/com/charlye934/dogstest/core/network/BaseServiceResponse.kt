package com.charlye934.dogstest.core.network

data class BaseServiceResponse<T>(
    val success: Boolean,
    val result: T?,
    val error: BaseError?
)