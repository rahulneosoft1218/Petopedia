package com.petopedia.app.di

import com.petopedia.app.data.PetsDataSource
import com.petopedia.app.data.PetsDataSourceImpl
import com.petopedia.app.domain.repository.PetsRepository
import com.petopedia.app.domain.repository.PetsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun providePetsDataSource(petsDataSourceImpl: PetsDataSourceImpl): PetsDataSource

    @Binds
    abstract fun providePetsRepository(petsRepositoryImpl: PetsRepositoryImpl): PetsRepository
}
