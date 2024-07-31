package com.petopedia.app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petopedia.app.domain.model.Breeds
import com.petopedia.app.domain.model.PetDetails
import com.petopedia.app.domain.usecase.GetPetInfoSearchUseCase
import com.petopedia.app.domain.usecase.GetPetInfoUseCase
import com.petopedia.app.domain.usecase.GetStatisticCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel
    @Inject
    constructor(
        private val getPetInfoUseCase: GetPetInfoUseCase,
        private val getPetInfoSearchUseCase: GetPetInfoSearchUseCase,
        private val getStatisticCountUseCase: GetStatisticCountUseCase,
    ) : ViewModel() {
        private val petDataHolder = MutableStateFlow(PetDetails(emptyList()))
        val petData: StateFlow<PetDetails> = petDataHolder.asStateFlow()

        private val charCount = MutableSharedFlow<List<Map.Entry<Char, Int>>>()
        val topCharCount: SharedFlow<List<Map.Entry<Char, Int>>> = charCount.asSharedFlow()

        fun getPetList(breed: String = "") =
            viewModelScope.launch(Dispatchers.IO) {
                petDataHolder.emit(getPetInfoUseCase.execute(GetPetInfoUseCase.Params(breed)))
            }

        fun getFilteredData(
            index: Int,
            input: String,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                petDataHolder.emit(getPetInfoSearchUseCase.execute(GetPetInfoSearchUseCase.Params(index, input)))
            }
        }

        fun getStatisticCount(breedsList: List<Breeds>) {
            viewModelScope.launch(Dispatchers.IO) {
                charCount.emit(getStatisticCountUseCase.execute(GetStatisticCountUseCase.Params(breedsList)))
            }
        }
    }
