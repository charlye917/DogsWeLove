package com.charlye934.dogstest.doglist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.charlye934.dogstest.doglist.data.local.dao.DogDao
import com.charlye934.dogstest.doglist.data.local.entities.DogsInfoEntity

@Database(
    entities = [DogsInfoEntity::class],
    version = 1
)
abstract class DogsInfoDataBase : RoomDatabase() {
    abstract fun getAllDogsDao(): DogDao
}