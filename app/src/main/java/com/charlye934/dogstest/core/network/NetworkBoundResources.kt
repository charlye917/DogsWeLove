package com.charlye934.dogstest.core.network

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<LocalType : Any, RemoteType, ResultType : Any> {

    fun asFlow(): Flow<TaskUiState<ResultType>> = flow {
        emit(TaskUiState.Loading)

        val localData = loadFromDb().firstOrNull()

        if (shouldFetch(localData)) {
            emit(TaskUiState.Loading)
            try {
                val remoteData = fetchFromNetwork()
                clearLocalData()

                saveNetworkResult(remoteData)
                emitAll(loadFromDb().map { TaskUiState.Success(data = convertToResultType(it)) })
            } catch (exception: Throwable) {
                emit(
                    TaskUiState.Error(
                        BaseError(
                            exception.localizedMessage ?: "Error desconocido",
                            code = ""
                        )
                    )
                )
            }
        } else {
            emitAll(loadFromDb().map { TaskUiState.Success(data = convertToResultType(it)) })
        }
    }

    @WorkerThread
    protected abstract suspend fun clearLocalData()

    @WorkerThread
    protected abstract suspend fun fetchFromNetwork(): RemoteType

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: RemoteType)

    protected abstract suspend fun loadFromDb(): Flow<LocalType>

    protected abstract fun shouldFetch(data: LocalType?): Boolean

    protected abstract fun convertToResultType(data: LocalType): ResultType
}