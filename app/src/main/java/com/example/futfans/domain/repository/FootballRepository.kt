package com.example.futfans.domain.repository

import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

interface FootballRepository {
    suspend fun getAllCountries(): Flow<List<CountryDto>>
    suspend fun filterCountriesByName(keyword: String): List<CountryDto>
    suspend fun getTeamByCountry(countryName: String): List<TeamDto>
    suspend fun filterTeamsByName(keyword: String, countryName: String): List<TeamDto>
}
