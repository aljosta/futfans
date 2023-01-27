package com.example.futfans.data.api.models

import com.google.gson.annotations.SerializedName

data class PagingEntity(
    @SerializedName("current")
    val current: Int,
    @SerializedName("total")
    val total: Int,
)
