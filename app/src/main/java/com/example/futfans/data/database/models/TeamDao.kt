package com.example.futfans.data.database.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teams: List<Team>)

    @Query("SELECT * FROM Team WHERE country = :countryName")
    fun getAllTeamsByCountry(countryName: String): List<Team>

    @Query("SELECT * FROM Team WHERE name = :teamName")
    fun getTeamByName(teamName: String): Team
}
