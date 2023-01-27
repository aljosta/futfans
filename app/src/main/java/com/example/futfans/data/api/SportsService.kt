package com.example.futfans.data.api

import com.example.futfans.data.api.models.country.CountryResponse
import com.example.futfans.data.api.models.team.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsService {
    @GET("countries")
    suspend fun getCountries(): CountryResponse

    @GET("teams")
    suspend fun getTeams(@Query("country") countryName: String): TeamResponse
}
