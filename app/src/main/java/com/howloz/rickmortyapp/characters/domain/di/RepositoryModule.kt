package com.howloz.rickmortyapp.characters.domain.di

import android.app.Application
import com.apollographql.apollo3.ApolloClient
import com.howloz.rickmortyapp.characters.data.repository.CharacterRepositoryImpl
import com.howloz.rickmortyapp.characters.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesCharacterRepository( application: Application, apolloClient: ApolloClient):CharacterRepository{
        return CharacterRepositoryImpl(application,apolloClient)
    }
}