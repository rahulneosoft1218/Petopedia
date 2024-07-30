package com.petopedia.app.domain.repository

import com.petopedia.app.data.PetsDataSource
import com.petopedia.app.domain.model.Animal
import com.petopedia.app.domain.model.Breeds
import com.petopedia.app.domain.model.PetDetails
import javax.inject.Inject

class PetsRepositoryImpl
    @Inject
    constructor(
        private val petsDataSource: PetsDataSource,
    ) : PetsRepository {
        override suspend fun getData(): PetDetails = petsDataSource.getData()

        override suspend fun getBreedsListFor(
            animal: Animal,
            icon: Int,
        ): List<Breeds> = petsDataSource.getBreedsListFor(animal, icon)

        override suspend fun filterList(
            index: Int,
            input: String,
        ) = petsDataSource.filterList(index, input)

        override suspend fun getStatisticCount(breeds: List<Breeds>): List<Map.Entry<Char, Int>> = petsDataSource.getStatisticCount(breeds)
    }
