package com.howloz.rickmortyapp.characters.domain.useCase

import com.apollographql.apollo3.ApolloClient
import com.howloz.rickmortyapp.characters.domain.data.Character
import com.howloz.rickmortyapp.characters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetAllCharactersDesc @Inject constructor(
    val repository: CharacterRepository
) {
    suspend fun execute(): List<Character>{
        return repository.getCharacters()
    }
}