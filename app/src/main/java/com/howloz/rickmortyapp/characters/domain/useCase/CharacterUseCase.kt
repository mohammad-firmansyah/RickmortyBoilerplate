package com.howloz.rickmortyapp.characters.domain.useCase

data class CharacterUseCase (
    val getAllCharactersDesc: GetAllCharactersDesc,
    val getCharacterById: GetCharacterById
)