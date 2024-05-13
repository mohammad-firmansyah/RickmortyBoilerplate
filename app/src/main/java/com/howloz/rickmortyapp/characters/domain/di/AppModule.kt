package com.howloz.rickmortyapp.characters.domain.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApollo(): ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }




}