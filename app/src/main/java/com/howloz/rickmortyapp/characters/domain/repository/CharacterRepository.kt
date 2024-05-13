package com.howloz.rickmortyapp.characters.domain.repository

import com.howloz.rickmortyapp.characters.domain.data.Character

interface CharacterRepository {
    suspend fun getCharacters():List<Character>
    suspend fun getCharacter(id:String):Character

}