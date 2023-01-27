package com.example.futfans.di

import com.example.futfans.data.api.ApiServiceProvider
import com.example.futfans.data.api.SportsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): SportsService {
        return ApiServiceProvider()
            .create(SportsService::class.java, "https://v3.football.api-sports.io/")
    }
}
