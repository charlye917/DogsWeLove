package com.charlye934.dogstest.doglist.data.repository

import com.charlye934.dogstest.core.network.TaskUiState
import com.charlye934.dogstest.doglist.data.repository.model.DogsRepositoryModel
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getAllDogsNetwork(isRefreshing: Boolean): Flow<TaskUiState<List<DogsRepositoryModel>>>
}