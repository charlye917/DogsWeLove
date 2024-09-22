package com.charlye934.dogstest.doglist.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.charlye934.dogstest.doglist.data.remote.model.response.DogsResponse
import com.charlye934.dogstest.doglist.data.repository.model.DogsRepositoryModel

@Entity(tableName = "dogs_info_table")
data class DogsInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "dogName") val dogName: String,
    @ColumnInfo(name = "descriptoin") val description: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "image") val image: String
)

fun List<DogsInfoEntity>.dogsListEntitytoRepositoryModel(): List<DogsRepositoryModel> =
    map {
        DogsRepositoryModel(
            dogName = it.dogName,
            description = it.description,
            age = it.age,
            image = it.image
        )
    }

fun List<DogsResponse>.dogsListModelToEntity(): List<DogsInfoEntity> =
    map {
        DogsInfoEntity(
            dogName = it.dogName ?: "",
            description = it.description ?: "",
            age = it.age ?: 0,
            image = it.image ?: ""
        )
    }

