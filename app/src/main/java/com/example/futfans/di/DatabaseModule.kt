package com.example.futfans.di

import android.app.Application
import com.example.futfans.data.database.SportsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideSportsDatabase(context: Application): SportsDatabase {
        return SportsDatabase(context)
    }
}
