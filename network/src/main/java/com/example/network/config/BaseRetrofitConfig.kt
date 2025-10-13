package com.example.network.config

import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter

open class BaseRetrofitConfig(
    override val baseUrl: String,
    override val connectTimeout: Long = 30_000,
    override val readTimeout: Long = 30_000,
    override val writeTimeout: Long = 30_000,
    override val interceptors: List<Interceptor> = emptyList(),
    override val converterFactories: List<Converter.Factory> = emptyList(),
    override val callAdapterFactories: List<CallAdapter.Factory> = emptyList()
) : RetrofitConfig