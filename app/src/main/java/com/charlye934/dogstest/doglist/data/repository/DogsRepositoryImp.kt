package com.charlye934.dogstest.doglist.data.repository

import android.content.Context
import com.charlye934.dogstest.core.network.BaseApiResponse
import com.charlye934.dogstest.core.network.TaskUiState
import com.charlye934.dogstest.doglist.data.remote.datasources.DogsRemoteDataSources
import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DogsRepositoryImp @Inject constructor(
    private val dataSoruces: DogsRemoteDataSources,
    @ApplicationContext private val context: Context
) : DogsRepository,
    BaseApiResponse() {

    override suspend fun getAllDogsNetwork(): Flow<TaskUiState<List<DogsResponse>>> = flow {
        emit(TaskUiState.Loading)
        emit(safeApiCall(context) { dataSoruces.getAllDogs() })
    }.flowOn(Dispatchers.IO)
}