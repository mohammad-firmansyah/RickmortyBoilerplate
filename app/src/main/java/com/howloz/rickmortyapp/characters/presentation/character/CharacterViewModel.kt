package com.howloz.rickmortyapp.characters.presentation.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.howloz.rickmortyapp.characters.domain.data.Character
import com.howloz.rickmortyapp.characters.domain.repository.CharacterRepository
import com.howloz.rickmortyapp.characters.domain.useCase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    useCase: CharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

        private val _state = MutableStateFlow(CharacterState())
        val state = _state

    init {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        savedStateHandle.get<String>("id").let {id ->
            viewModelScope.launch {
                val result = useCase.getCharacterById.execute(id.toString())
                _state.update {
                    it.copy(
                        isLoading = false,
                        character = result
                    )
                }
            }
        }

    }



    }