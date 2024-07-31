package com.petopedia.app.data

import com.petopedia.app.R
import com.petopedia.app.domain.model.Animal
import com.petopedia.app.domain.model.Breeds
import com.petopedia.app.domain.model.PetDetails
import com.petopedia.app.domain.model.Species
import com.petopedia.app.domain.model.Statistic
import javax.inject.Inject

class PetsDataSourceImpl
    @Inject
    constructor() : PetsDataSource {
        override suspend fun getData() =
            PetDetails(
                speciesList = prepareDataForPetBreeds(),
            )

        private suspend fun prepareDataForPetBreeds(): List<Species> =
            arrayListOf<Species>().apply {
                add(
                    Species(
                        id = Animal.Cat,
                        petName = R.string.name_cat,
                        breedsList = getBreedsListFor(Animal.Cat, R.drawable.cat),
                        image = R.drawable.cat,
                    ),
                )
                add(
                    Species(
                        id = Animal.Dog,
                        petName = R.string.name_dog,
                        breedsList = getBreedsListFor(Animal.Dog, R.drawable.dog),
                        image = R.drawable.dog,
                    ),
                )
                add(
                    Species(
                        id = Animal.Sheep,
                        petName = R.string.name_sheep,
                        breedsList = getBreedsListFor(Animal.Sheep, R.drawable.sheep),
                        image = R.drawable.sheep,
                    ),
                )
                add(
                    Species(
                        id = Animal.Rabbit,
                        petName = R.string.name_rabbit,
                        breedsList = getBreedsListFor(Animal.Rabbit, R.drawable.rabbit),
                        image = R.drawable.rabbit,
                    ),
                )
                add(
                    Species(
                        id = Animal.Parrot,
                        petName = R.string.name_parrot,
                        breedsList = getBreedsListFor(Animal.Parrot, R.drawable.parrot),
                        image = R.drawable.parrot,
                    ),
                )
            }

        override suspend fun getBreedsListFor(
            animal: Animal,
            icon: Int,
        ) = animal.getAnimalData(icon)

        override suspend fun filterList(
            index: Int,
            input: String,
        ): PetDetails =
            PetDetails(
                getData().speciesList.toMutableList().apply {
                    this[index] =
                        this[index].copy(
                            breedsList =
                                getData().speciesList[index].breedsList.filter {
                                    it.title.contains(input, ignoreCase = true)
                                },
                        )
                },
            )

        override suspend fun getStatisticCount(breeds: List<Breeds>): List<Map.Entry<Char, Int>> {
            val charFrequency =
                breeds
                    .joinToString(separator = "") { it.title.filterNot { char -> char.isWhitespace() } }
                    .groupingBy { it }
                    .eachCount()
                    .entries
                    .sortedByDescending { it.value }
            return charFrequency.take(Statistic.MAX.count)
        }
    }

private fun Animal.getAnimalData(icon: Int): List<Breeds> {
    val breedList =
        when (this) {
            Animal.Cat ->
                listOf(
                    "Siamese cat",
                    "British Shorthair",
                    "Maine Coon",
                    "Persian cat",
                    "Ragdoll",
                    "Sphynx cat",
                    "American Shorthair",
                    "Abyssinian",
                    "Munchkin cat",
                    "Russian Blue",
                )
            Animal.Dog ->
                listOf(
                    "German Shepherd",
                    "Bulldog",
                    "Labrador Retriever",
                    "Golden Retriever",
                    "French Bulldog",
                    "Siberian Husky",
                    "Alaskan Malamute",
                    "Poodle",
                    "American Eskimo Dog",
                    "Chow Chow",
                )
            Animal.Sheep ->
                listOf(
                    "Dorper",
                    "Valais Blacknose",
                    "Merino",
                    "Suffolk sheep",
                    "Texel sheep",
                    "Awassi",
                    "Cameroon sheep",
                    "Priangan",
                    "Dorset Horn",
                    "Ladoum",
                    "Herdwick",
                    "Dorset",
                )
            Animal.Rabbit ->
                listOf(
                    "American Fuzzy Lop",
                    "Lionhead rabbit",
                    "Rex rabbit",
                    "Holland Lop",
                    "Angora rabbit",
                    "Cashmere Lop",
                    "Mini Lop",
                    "Mini Rex",
                    "Californian rabbit",
                    "English Lop",
                    "Polish rabbit",
                )
            Animal.Parrot ->
                listOf(
                    "African Grey Parrots",
                    "Cockatoos",
                    "Macaws",
                    "Parrotlet",
                    "Cockatiel",
                    "Senegal Parrot",
                    "Parakeets",
                    "Eclectus Parrots",
                    "Monk parakeet",
                    "Rainbow lorikeet",
                    "Moluccan eclectus",
                )
        }

    return breedList
        .map { name ->
            Breeds(
                title = name,
                subTitle = this.name,
                icon = icon,
            )
        }.toCollection(ArrayList())
}
