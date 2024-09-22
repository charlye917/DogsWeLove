package com.charlye934.dogstest.doglist.di

import com.charlye934.dogstest.doglist.data.remote.datasources.DogsRemoteDataSources
import com.charlye934.dogstest.doglist.data.remote.datasources.DogsRemoteDataSourcesImp
import com.charlye934.dogstest.doglist.data.repository.DogsRepository
import com.charlye934.dogstest.doglist.data.repository.DogsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DogsListModule {

    @Binds
    abstract fun dogsRemoteDataSourcesModule(dogsRemoteDataSourcesModuleImp: DogsRemoteDataSourcesImp): DogsRemoteDataSources

    @Binds
    abstract fun dogsRepositoryModule(dogsRepositoryImp: DogsRepositoryImp): DogsRepository
}