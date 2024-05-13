package com.howloz.rickmortyapp.characters.presentation.characters

import com.howloz.rickmortyapp.characters.domain.data.Character

data class CharacterListState(
    val isLoading:Boolean = false,
    val characterList: List<Character> = emptyList()
)