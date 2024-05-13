package com.howloz.rickmortyapp.characters.data.mapper

import com.howloz.rickmortyapp.GetAllCharactersQuery
import com.howloz.rickmortyapp.GetDetailCharacterQuery
import com.howloz.rickmortyapp.characters.domain.data.Character

object Mapper {

    fun GetAllCharactersQuery.Characters.toList() :List<Character>{
        val characters = mutableListOf<Character>()
        this.results?.forEach {
            val character = Character(
                it?.id,
                it?.name,
                it?.status,
                it?.status
            )

            characters.add(character)
        }
        return characters
    }

    fun GetDetailCharacterQuery.Character.toCharacter() :Character{
        val character = Character(
            this.id,
            this.name,
            this.status,
            this.image
        )

        return character
    }
}