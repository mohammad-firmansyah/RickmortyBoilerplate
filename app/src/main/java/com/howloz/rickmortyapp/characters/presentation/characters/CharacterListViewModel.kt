package com.howloz.rickmortyapp.characters.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.howloz.rickmortyapp.characters.domain.useCase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(CharacterListState())
    val state = _state

    init {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val result = useCase.getAllCharactersDesc.execute()
            _state.update {
                it.copy(
                    isLoading = false,
                    characterList = result
                )
            }
        }


    }
}