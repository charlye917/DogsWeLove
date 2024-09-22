package com.charlye934.dogstest.core.network

sealed class TaskUiState<out T> {
    data object Loading : TaskUiState<Nothing>()
    data class Success<out T : Any>(val data: T) : TaskUiState<T>()
    data class Error(val error: BaseError) : TaskUiState<Nothing>()
}