package com.petopedia.app.data

import com.petopedia.app.domain.model.Animal
import com.petopedia.app.domain.model.Breeds
import com.petopedia.app.domain.model.PetDetails

interface PetsDataSource {
    suspend fun getData(): PetDetails

    suspend fun getBreedsListFor(
        animal: Animal,
        icon: Int,
    ): List<Breeds>

    suspend fun filterList(
        index: Int,
        input: String,
    ): PetDetails

    suspend fun getStatisticCount(breeds: List<Breeds>): List<Map.Entry<Char, Int>>
}
