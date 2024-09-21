package com.charlye934.dogstest.doglist.data.remote.datasources

import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.doglist.data.remote.service.DogApiService
import com.charlye934.dogstest.core.network.BaseServiceResponse
import javax.inject.Inject

class DogsRemoteDataSourcesImp @Inject constructor(
    private val apiClient: DogApiService
): DogsRemoteDataSources {

    override suspend fun getAllDogs(): BaseServiceResponse<List<DogsResponse>> =
        apiClient.getDogs()
}