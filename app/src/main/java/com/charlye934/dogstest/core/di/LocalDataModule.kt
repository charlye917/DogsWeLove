package com.charlye934.dogstest.core.di

import android.content.Context
import androidx.room.Room
import com.charlye934.dogstest.doglist.data.local.database.DogsInfoDataBase
import com.charlye934.dogstest.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DogsInfoDataBase::class.java, DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideDataDogsDao(dataBase: DogsInfoDataBase) = dataBase.getAllDogsDao()

}