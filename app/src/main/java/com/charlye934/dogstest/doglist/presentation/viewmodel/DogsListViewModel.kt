package com.charlye934.dogstest.doglist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlye934.dogstest.core.network.TaskUiState
import com.charlye934.dogstest.core.network.TaskUiState.Success
import com.charlye934.dogstest.doglist.domain.usecase.GetDogsUseCase
import com.charlye934.dogstest.doglist.presentation.state.DogsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsListViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DogsState())
    val uiState = _uiState.asStateFlow()

    fun getDogs() {
        viewModelScope.launch {
            getAllListDogsData()
        }
    }

    private suspend fun getAllListDogsData() {
        getDogsUseCase().collect {
            when (it) {
                is TaskUiState.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }

                is Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isError = false,
                        listDogs = it.data
                    )
                }

                is TaskUiState.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }
}