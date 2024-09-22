package com.charlye934.dogstest.doglist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charlye934.dogstest.doglist.data.local.entities.DogsInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogsList(dogs: List<DogsInfoEntity>)

    @Query("SELECT * FROM dogs_info_table")
    fun getAllDogs(): Flow<List<DogsInfoEntity>>

    @Query("DELETE FROM dogs_info_table")
    suspend fun deleteDogList()


}