package com.howloz.rickmortyapp.characters.presentation.character

import com.howloz.rickmortyapp.characters.domain.data.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val character : Character = Character()
)