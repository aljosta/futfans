package com.example.futfans.di

import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.model.TeamDto
import com.example.futfans.domain.usecase.FilterCountriesByNameUseCase
import com.example.futfans.domain.usecase.FilterTeamsByNameUseCase
import com.example.futfans.domain.usecase.GetCountriesUseCase
import com.example.futfans.domain.usecase.GetTeamsUseCase
import com.example.futfans.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideGetAllCountries(
        implementation: GetCountriesUseCase
    ): UseCase<Any, Flow<List<CountryDto>>> = implementation

    @Provides
    fun provideFilterCountriesByName(
        implementation: FilterCountriesByNameUseCase
    ): UseCase<FilterCountriesByNameUseCase.Params, List<CountryDto>> = implementation

    @Provides
    fun provideGetAllTeams(
        implementation: GetTeamsUseCase
    ): UseCase<GetTeamsUseCase.Params, List<TeamDto>> = implementation

    @Provides
    fun provideFilterTeamsByName(
        implementation: FilterTeamsByNameUseCase
    ): UseCase<FilterTeamsByNameUseCase.Params, List<TeamDto>> = implementation
}
