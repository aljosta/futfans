package com.example.futfans.data.datasource.local

import com.example.futfans.data.database.SportsDatabase
import com.example.futfans.data.datasource.FootballLocalSource
import com.example.futfans.domain.mapper.CountryMapper
import com.example.futfans.domain.mapper.TeamMapper
import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.model.TeamDto
import javax.inject.Inject

class FootballLocalDataSource @Inject constructor(
    val database: SportsDatabase
) : FootballLocalSource {

    private val countryDao by lazy { database.countryDao() }
    private val teamDao by lazy { database.teamDao() }

    override suspend fun saveCountries(countries: List<CountryDto>): Boolean {
        return try {
            countryDao.insertAllCountries(
                CountryMapper.transformCollectionDtoToDBModel(countries)
            )
            true
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun getAllCountries(): List<CountryDto> {
        return try {
            CountryMapper.transformCollectionDBModelToDto(
                countryDao.getAllCountries()
            )
        } catch (exception: Exception) {
            emptyList()
        }
    }

    override suspend fun getAllTeamsByCountry(countryName: String): List<TeamDto> {
        return try {
            teamDao.getAllTeamsByCountry(countryName).map {
                TeamMapper.transformDBModelToDto(it)
            }
        } catch (exception: Exception) {
            emptyList()
        }
    }

    override suspend fun saveTeams(teams: List<TeamDto>): Boolean {
        return try {
            teamDao.insertAllTeams(
                teams.map { TeamMapper.transformDtoToDBModel(it) }
            )
            true
        } catch (exception: Exception) {
            false
        }
    }
}
