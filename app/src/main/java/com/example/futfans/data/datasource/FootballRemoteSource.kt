package com.example.futfans.data.datasource

import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.model.TeamDto

interface FootballRemoteSource {
    suspend fun getAllCountries(): List<CountryDto>
    suspend fun getTeamsByCountry(countryName: String): List<TeamDto>
}
