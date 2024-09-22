package com.charlye934.dogstest.doglist.domain.mapper

import com.charlye934.dogstest.doglist.data.repository.model.DogsRepositoryModel
import com.charlye934.dogstest.doglist.domain.model.DogsUI

fun dogsListResponseToUI(list: List<DogsRepositoryModel>) =
    list.map {
        DogsUI(
            dogName = it.dogName ?: "",
            dogDescription = it.description ?: "",
            age = it.age.toString(),
            image = it.image ?: ""
        )
    }