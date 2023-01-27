package com.example.futfans.di

import com.example.futfans.data.api.SportsService
import com.example.futfans.data.database.SportsDatabase
import com.example.futfans.data.datasource.FootballLocalSource
import com.example.futfans.data.datasource.FootballRemoteSource
import com.example.futfans.data.datasource.local.FootballLocalDataSource
import com.example.futfans.data.datasource.remote.FootballRemoteDataSource
import com.example.futfans.data.repository.FootballDataRepository
import com.example.futfans.domain.repository.FootballRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: SportsService): FootballRemoteSource =
        FootballRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideLocalDataSource(database: SportsDatabase): FootballLocalSource =
        FootballLocalDataSource(database)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: FootballRemoteDataSource,
        localDataSource: FootballLocalDataSource
    ): FootballRepository = FootballDataRepository(remoteDataSource, localDataSource)
}
