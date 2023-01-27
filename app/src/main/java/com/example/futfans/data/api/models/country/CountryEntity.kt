package com.example.futfans.data.api.models.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(defaultValue = "")
    @SerializedName("code")
    val code: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @ColumnInfo(defaultValue = "")
    @SerializedName("name")
    val name: String? = null,
    @ColumnInfo(defaultValue = "")
    @SerializedName("flag")
    val flag: String? = null,
)
