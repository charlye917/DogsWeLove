package com.charlye934.dogstest.doglist.data.repository

import com.charlye934.dogstest.core.network.Resources
import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getAllDogsNetwork(): Flow<Resources<List<DogsResponse>>>
}