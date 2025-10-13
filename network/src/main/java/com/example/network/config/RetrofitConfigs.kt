package com.example.network.config

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

enum class ApiType {
    DEFAULT
}

object RetrofitConfigs {

    val DEFAULT = BaseRetrofitConfig(
        baseUrl = "https://drive.usercontent.google.com",
        connectTimeout = 30_000,
        readTimeout = 30_000,
        writeTimeout = 30_000,
        interceptors = listOf(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        ),
        converterFactories = listOf(GsonConverterFactory.create())
    )
}