package com.charlye934.dogstest.doglist.domain.usecase

import android.util.Log
import com.charlye934.dogstest.core.network.BaseError
import com.charlye934.dogstest.core.network.TaskUiState
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
    suspend operator fun invoke(): Flow<TaskUiState<List<DogsUI>>> = flow {
        repository.getAllDogsNetwork()
            .catch { e -> e.printStackTrace() }
            .collect { state ->
                when (state) {
                    is TaskUiState.Loading -> {
                        emit(TaskUiState.Loading)
                    }

                    is TaskUiState.Success -> {
                        Log.d("__tag data", state.data.toString())
                        val data = state.data
                        val newData = dogsListResponseToUI(data)
                        emit(TaskUiState.Success(data = newData))
                    }

                    is TaskUiState.Error -> {
                        Log.d("__tag data", state.error.toString())
                        val error = state
                        emit(
                            TaskUiState.Error(
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