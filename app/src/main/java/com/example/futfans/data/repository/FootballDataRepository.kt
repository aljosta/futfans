package com.example.futfans.data.repository

import com.example.futfans.data.datasource.FootballLocalSource
import com.example.futfans.data.datasource.FootballRemoteSource
import com.example.futfans.domain.repository.FootballRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FootballDataRepository @Inject constructor(
    private val remoteDataSource: FootballRemoteSource,
    private val localDataSource: FootballLocalSource,
) : FootballRepository {

    override suspend fun getAllCountries() = coroutineScope {
        flow {
            var countries = localDataSource.getAllCountries()
            if (countries.isEmpty()) {
                countries = remoteDataSource.getAllCountries()
                async {
                    localDataSource.saveCountries(countries)
                }
            }
            emit(countries)
        }
    }

    override suspend fun filterCountriesByName(keyword: String) =
        localDataSource.getAllCountries()
            .filter { it.name?.lowercase()?.startsWith(keyword.lowercase()) == true }

    override suspend fun getTeamByCountry(countryName: String) = coroutineScope {
        var teams = localDataSource.getAllTeamsByCountry(countryName)
        return@coroutineScope teams.ifEmpty {
            teams = remoteDataSource.getTeamsByCountry(countryName)
            async {
                localDataSource.saveTeams(teams)
            }
            teams
        }
    }

    override suspend fun filterTeamsByName(keyword: String, countryName: String) =
        localDataSource.getAllTeamsByCountry(countryName)
            .filter { it.name?.lowercase()?.startsWith(keyword.lowercase()) == true }
}
