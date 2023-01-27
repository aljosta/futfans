package com.example.futfans.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceProvider : ApiProvider {
    override fun <T> create(apiService: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiService)
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .addHeader("x-rapidapi-key", "9f945f166dbd9b08a096238c6c4bbfec")
                        .build()
                )
            }
            .build()
    }
}
