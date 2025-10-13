package com.example.network.config

import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter

interface RetrofitConfig {
    val baseUrl: String
    val connectTimeout: Long
    val readTimeout: Long
    val writeTimeout: Long
    val interceptors: List<Interceptor>
    val converterFactories: List<Converter.Factory>
    val callAdapterFactories: List<CallAdapter.Factory>
}