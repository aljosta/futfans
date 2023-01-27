package com.example.futfans.data.api.models.team

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class TeamEntity(
    @SerializedName("team")
    val team: TeamInformationEntity? = null,
    @SerializedName("venue")
    val venue: VenueEntity? = null,
)
