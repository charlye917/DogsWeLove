package com.charlye934.dogstest.doglist.data.remote.service

import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.utils.Constants.PATH_LIST_DOGS
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET(PATH_LIST_DOGS)
    suspend fun getDogs(): Response<List<DogsResponse>>
}
