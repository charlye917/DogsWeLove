package com.charlye934.dogstest.doglist.data.remote.datasources

import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.doglist.data.remote.service.DogApiService
import retrofit2.Response
import javax.inject.Inject

class DogsRemoteDataSourcesImp @Inject constructor(
    private val apiClient: DogApiService
) : DogsRemoteDataSources {

    override suspend fun getAllDogs(): Response<List<DogsResponse>> =
        apiClient.getDogs()
}