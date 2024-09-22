package com.charlye934.dogstest.doglist.domain.usecase

import com.charlye934.dogstest.core.network.BaseError
import com.charlye934.dogstest.core.network.Resources
import com.charlye934.dogstest.doglist.data.repository.DogsRepository
import com.charlye934.dogstest.doglist.domain.mapper.dogsListResponseToUI
import com.charlye934.dogstest.doglist.domain.model.DogsUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val repository: DogsRepository
) {
    suspend operator fun invoke(): Flow<Resources<List<DogsUI>>> = flow {
        repository.getAllDogsNetwork()
            .catch { e -> e.printStackTrace() }
            .collect { state ->
                when (state) {
                    is Resources.Loading -> {
                        emit(Resources.Loading)
                    }

                    is Resources.Success -> {
                        val data = state.data
                        val newData = dogsListResponseToUI(data)
                        emit(Resources.Success(data = newData))
                    }

                    else -> {
                        val error = state as Resources.Error
                        emit(
                            Resources.Error(
                                BaseError(
                                    message = error.error.message,
                                    code = error.error.code
                                )
                            )
                        )
                    }
                }
            }
    }.flowOn(Dispatchers.IO)
}