package com.charlye934.dogstest.doglist.data.remote.service

import com.charlye934.dogstest.core.network.BaseServiceResponse
import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import retrofit2.http.GET

interface DogApiService {

    @GET("1151549092634943488")
    suspend fun getDogs(): BaseServiceResponse<List<DogsResponse>>
}
