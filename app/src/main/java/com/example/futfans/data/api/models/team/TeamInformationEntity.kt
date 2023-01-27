package com.example.futfans.data.api.models.team

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class TeamInformationEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("founded")
    val founded: Int? = null,
    @SerializedName("national")
    val national: Boolean? = null,
    @SerializedName("logo")
    val logo: String? = null,
)
