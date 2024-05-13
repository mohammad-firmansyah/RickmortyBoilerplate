package com.howloz.rickmortyapp.characters.data.repository

import android.app.Application
import com.apollographql.apollo3.ApolloClient
import com.howloz.rickmortyapp.GetAllCharactersQuery
import com.howloz.rickmortyapp.GetDetailCharacterQuery
import com.howloz.rickmortyapp.characters.data.mapper.Mapper.toCharacter
import com.howloz.rickmortyapp.characters.data.mapper.Mapper.toList
import com.howloz.rickmortyapp.characters.domain.data.Character
import com.howloz.rickmortyapp.characters.domain.repository.CharacterRepository
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(val application: Application,val apolloClient: ApolloClient):CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
        try {
            val response = apolloClient.query(GetAllCharactersQuery()).execute()

            return response.data?.characters?.toList()!!
        }catch (e:Exception){
            return listOf()
        }

    }

    override suspend fun getCharacter(id: String): Character {
        try {
            val response = apolloClient.query(GetDetailCharacterQuery(id)).execute()
            return response.data?.character?.toCharacter()!!
        }catch (e:Exception){
            return Character()
        }
    }
}