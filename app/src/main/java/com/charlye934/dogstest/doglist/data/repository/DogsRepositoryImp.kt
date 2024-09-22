package com.charlye934.dogstest.doglist.data.repository

import android.content.Context
import com.charlye934.dogstest.core.network.BaseApiResponse
import com.charlye934.dogstest.core.network.NetworkBoundResource
import com.charlye934.dogstest.core.network.TaskUiState
import com.charlye934.dogstest.doglist.data.local.dao.DogDao
import com.charlye934.dogstest.doglist.data.local.entities.DogsInfoEntity
import com.charlye934.dogstest.doglist.data.local.entities.dogsListEntitytoRepositoryModel
import com.charlye934.dogstest.doglist.data.local.entities.dogsListModelToEntity
import com.charlye934.dogstest.doglist.data.remote.datasources.DogsRemoteDataSources
import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.doglist.data.repository.model.DogsRepositoryModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogsRepositoryImp @Inject constructor(
    private val remoteDataSource: DogsRemoteDataSources,
    private val localDataSource: DogDao,
    @ApplicationContext private val context: Context
) : DogsRepository, BaseApiResponse() {

    override suspend fun getAllDogsNetwork(isRefreshing: Boolean): Flow<TaskUiState<List<DogsRepositoryModel>>> {
        return object :
            NetworkBoundResource<List<DogsInfoEntity>, List<DogsResponse>, List<DogsRepositoryModel>>() {
            override suspend fun loadFromDb(): Flow<List<DogsInfoEntity>> {
                return localDataSource.getAllDogs()
            }

            override suspend fun fetchFromNetwork(): List<DogsResponse> {
                val result = safeApiCall(context) { remoteDataSource.getAllDogs() }

                return when (result) {
                    is TaskUiState.Success -> result.data
                    is TaskUiState.Error -> throw Exception(result.error.message)
                    else -> throw Exception("Unexpected error")
                }
            }

            override fun convertToResultType(data: List<DogsInfoEntity>): List<DogsRepositoryModel> {
                return data.dogsListEntitytoRepositoryModel()
            }

            override fun shouldFetch(data: List<DogsInfoEntity>?): Boolean {
                return data.isNullOrEmpty() || isRefreshing
            }

            override suspend fun clearLocalData() {
                return localDataSource.deleteDogList()
            }

            override suspend fun saveNetworkResult(item: List<DogsResponse>) {
                val dogsEntities = item.dogsListModelToEntity()
                localDataSource.insertDogsList(dogsEntities)
            }
        }.asFlow()
    }
}