package com.petopedia.app.domain.usecase

import com.petopedia.app.domain.model.PetDetails
import com.petopedia.app.domain.repository.PetsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPetInfoUseCase
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : UseCase<GetPetInfoUseCase.Params, Any>(Dispatchers.IO) {
        override suspend fun execute(params: Params): PetDetails = petsRepository.getData()

        data class Params(
            val name: String,
        )
    }
