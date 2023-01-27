package com.example.futfans.data.api.models.country

import com.example.futfans.data.api.models.PagingEntity
import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("get")
    val get: String,
    @SerializedName("errors")
    val errors: Map<String, String>,
    @SerializedName("results")
    val results: Int,
    @SerializedName("paging")
    val paging: PagingEntity,
    @SerializedName("response")
    val response: List<CountryEntity> = emptyList(),
)
