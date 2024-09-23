package com.charlye934.dogstest.doglist.presentation.state

import com.charlye934.dogstest.doglist.domain.model.DogsUI

data class DogsState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val listDogs: List<DogsUI> = mutableListOf()
)