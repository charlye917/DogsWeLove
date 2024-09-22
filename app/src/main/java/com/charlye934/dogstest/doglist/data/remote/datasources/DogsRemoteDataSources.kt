package com.charlye934.dogstest.doglist.data.remote.datasources

import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import retrofit2.Response

interface DogsRemoteDataSources {
    suspend fun getAllDogs(): Response<List<DogsResponse>>
}