package com.howloz.rickmortyapp.characters.domain.useCase

import com.howloz.rickmortyapp.characters.domain.data.Character
import com.howloz.rickmortyapp.characters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterById @Inject constructor(
    val repository: CharacterRepository
) {

    suspend fun execute(id:String):Character{
        return repository.getCharacter(id)
    }
}