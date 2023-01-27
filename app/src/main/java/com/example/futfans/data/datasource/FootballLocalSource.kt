package com.example.futfans.data.datasource

import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.model.TeamDto

interface FootballLocalSource {
    suspend fun saveCountries(countries: List<CountryDto>): Boolean
    suspend fun getAllCountries(): List<CountryDto>
    suspend fun getAllTeamsByCountry(countryName: String): List<TeamDto>
    suspend fun saveTeams(teams: List<TeamDto>): Boolean
}
