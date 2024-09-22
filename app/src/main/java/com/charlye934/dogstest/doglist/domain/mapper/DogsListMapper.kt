package com.charlye934.dogstest.doglist.domain.mapper

import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.doglist.domain.model.DogsUI

fun dogsListResponseToUI(list: List<DogsResponse>) =
    list.map {
        DogsUI(
            dogName = it.dogName ?: "",
            dogDescription = it.dogDescription ?: "",
            age = it.age.toString(),
            image = it.image ?: ""
        )
    }