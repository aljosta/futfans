package com.example.futfans.data.api.models.team

import com.google.gson.annotations.SerializedName

data class VenueEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("capacity")
    val capacity: Int? = null,
    @SerializedName("surface")
    val surface: String? = null,
    @SerializedName("image")
    val image: String? = null,
)
