package com.charlye934.dogstest.doglist.data.remote.datasources

import com.charlye934.dogstest.core.network.BaseServiceResponse
import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse

interface DogsRemoteDataSources {
    suspend fun getAllDogs(): BaseServiceResponse<List<DogsResponse>>
}