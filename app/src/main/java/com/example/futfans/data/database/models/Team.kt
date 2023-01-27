package com.example.futfans.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey
    val id: Int,
    val name: String,
    val code: String,
    val country: String,
    val founded: Int,
    val logo: String,
    @ColumnInfo(name = "stadium_id")
    val stadiumId: Int,
    @ColumnInfo(name = "stadium_name")
    val stadiumName: String,
    @ColumnInfo(name = "stadium_address")
    val stadiumAddress: String,
    @ColumnInfo(name = "stadium_city")
    val stadiumCity: String,
    @ColumnInfo(name = "stadium_capacity")
    val stadiumCapacity: Int,
    @ColumnInfo(name = "stadium_surface")
    val stadiumSurface: String,
    @ColumnInfo(name = "stadium_image")
    val stadiumImage: String
)
