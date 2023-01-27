package com.example.futfans.data.database.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountries(countries: List<Country>)

    @Query("SELECT * FROM Country")
    fun getAllCountries(): List<Country>

    @Query("SELECT * FROM Country WHERE name = :countryName")
    fun getCountryByName(countryName: String): Country

    @Query("DELETE FROM Country")
    fun deleteAll()
}
