package com.example.futfans.data.api

interface ApiProvider {
    fun <T> create(apiService: Class<T>, baseUrl: String): T
}
