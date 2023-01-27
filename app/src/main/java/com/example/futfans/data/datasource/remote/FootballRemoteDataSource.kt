package com.example.futfans.data.datasource.remote

import com.example.futfans.data.api.SportsService
import com.example.futfans.data.datasource.FootballRemoteSource
import com.example.futfans.domain.mapper.CountryMapper
import com.example.futfans.domain.mapper.TeamMapper
import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.model.TeamDto
import java.lang.Exception
import javax.inject.Inject

class FootballRemoteDataSource @Inject constructor(
    private val apiService: SportsService
) : FootballRemoteSource {
    override suspend fun getAllCountries(): List<CountryDto> {
        val countryResponse = apiService.getCountries()
        return if (countryResponse.errors.isEmpty()) {
            CountryMapper.transformCollectionApiModelToDto(
                countryResponse.response
            )
        } else {
            throw (Exception(countryResponse.errors.values.firstOrNull()))
        }
    }

    override suspend fun getTeamsByCountry(countryName: String): List<TeamDto> {
        val teamResponse = apiService.getTeams(countryName)
        return if (teamResponse.errors.isEmpty()) {
            TeamMapper.transformCollectionEntityToDto(teamResponse.response)
        } else {
            throw (Exception(teamResponse.errors.values.firstOrNull()))
        }
    }
}
