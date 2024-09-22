package com.charlye934.dogstest.doglist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.charlye934.dogstest.doglist.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogsListViewModel @Inject constructor(
    private val dogsUseCase: GetDogsUseCase
) : ViewModel()