package com.howloz.rickmortyapp.characters.domain.di

import com.apollographql.apollo3.ApolloClient
import com.howloz.rickmortyapp.characters.domain.repository.CharacterRepository
import com.howloz.rickmortyapp.characters.domain.useCase.CharacterUseCase
import com.howloz.rickmortyapp.characters.domain.useCase.GetAllCharactersDesc
import com.howloz.rickmortyapp.characters.domain.useCase.GetCharacterById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: CharacterRepository): CharacterUseCase {
        return CharacterUseCase(
            getAllCharactersDesc = GetAllCharactersDesc(repository),
            getCharacterById = GetCharacterById(repository)
        )
    }
}