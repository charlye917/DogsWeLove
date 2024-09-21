package com.charlye934.dogstest.doglist.data.remote.datasources

import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.core.network.BaseServiceResponse

interface DogsRemoteDataSources {
    suspend fun getAllDogs(): BaseServiceResponse<List<DogsResponse>>
}