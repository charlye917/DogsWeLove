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
    suspend operator fun invoke(isRefreshing: Boolean): Flow<TaskUiState<List<DogsUI>>> = flow {
        repository.getAllDogsNetwork(isRefreshing)
            .catch { e ->
                emit(
                    TaskUiState.Error(
                        BaseError(
                            message = e.localizedMessage ?: "Unknown error", code = ""
                        )
                    )
                )
            }
            .collect { state ->
                when (state) {
                    is TaskUiState.Loading -> {
                        emit(TaskUiState.Loading)
                    }

                    is TaskUiState.Success -> {

                        val uiData = dogsListResponseToUI(state.data)
                        emit(TaskUiState.Success(data = uiData))
                    }

                    is TaskUiState.Error -> {
                        Log.d("__tag error", state.error.message.toString())
                        emit(
                            TaskUiState.Error(
                                BaseError(
                                    message = state.error.message,
                                    code = state.error.code
                                )
                            )
                        )
                    }
                }
            }
    }.flowOn(Dispatchers.IO)
}