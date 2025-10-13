package com.example.network.builder

import com.example.network.config.ApiType
import com.example.network.config.BaseRetrofitConfig
import com.example.network.config.RetrofitConfig
import com.example.network.config.RetrofitConfigs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RetrofitClientBuilder private constructor() {

    private var config: RetrofitConfig = RetrofitConfigs.DEFAULT

    companion object {
        fun create(): RetrofitClientBuilder = RetrofitClientBuilder()
    }

    fun withConfig(config: RetrofitConfig): RetrofitClientBuilder {
        this.config = config
        return this
    }

    fun withApiType(apiType: ApiType): RetrofitClientBuilder {
        this.config = when (apiType) {
            ApiType.DEFAULT -> RetrofitConfigs.DEFAULT
        }
        return this
    }

    fun withBaseUrl(baseUrl: String): RetrofitClientBuilder {
        this.config = BaseRetrofitConfig(
            baseUrl = baseUrl,
            connectTimeout = config.connectTimeout,
            readTimeout = config.readTimeout,
            writeTimeout = config.writeTimeout,
            interceptors = config.interceptors,
            converterFactories = config.converterFactories,
            callAdapterFactories = config.callAdapterFactories
        )
        return this
    }

    fun withTimeouts(
        connectTimeout: Long? = null,
        readTimeout: Long? = null,
        writeTimeout: Long? = null
    ): RetrofitClientBuilder {
        this.config = BaseRetrofitConfig(
            baseUrl = config.baseUrl,
            connectTimeout = connectTimeout ?: config.connectTimeout,
            readTimeout = readTimeout ?: config.readTimeout,
            writeTimeout = writeTimeout ?: config.writeTimeout,
            interceptors = config.interceptors,
            converterFactories = config.converterFactories,
            callAdapterFactories = config.callAdapterFactories
        )
        return this
    }

    fun addInterceptor(interceptor: Interceptor): RetrofitClientBuilder {
        val newInterceptors = config.interceptors.toMutableList().apply {
            add(interceptor)
        }
        this.config = BaseRetrofitConfig(
            baseUrl = config.baseUrl,
            connectTimeout = config.connectTimeout,
            readTimeout = config.readTimeout,
            writeTimeout = config.writeTimeout,
            interceptors = newInterceptors,
            converterFactories = config.converterFactories,
            callAdapterFactories = config.callAdapterFactories
        )
        return this
    }

    fun addConverterFactory(converterFactory: Converter.Factory): RetrofitClientBuilder {
        val newConverters = config.converterFactories.toMutableList().apply {
            add(converterFactory)
        }
        this.config = BaseRetrofitConfig(
            baseUrl = config.baseUrl,
            connectTimeout = config.connectTimeout,
            readTimeout = config.readTimeout,
            writeTimeout = config.writeTimeout,
            interceptors = config.interceptors,
            converterFactories = newConverters,
            callAdapterFactories = config.callAdapterFactories
        )
        return this
    }

    fun addCallAdapterFactory(callAdapterFactory: CallAdapter.Factory): RetrofitClientBuilder {
        val newAdapters = config.callAdapterFactories.toMutableList().apply {
            add(callAdapterFactory)
        }
        this.config = BaseRetrofitConfig(
            baseUrl = config.baseUrl,
            connectTimeout = config.connectTimeout,
            readTimeout = config.readTimeout,
            writeTimeout = config.writeTimeout,
            interceptors = config.interceptors,
            converterFactories = config.converterFactories,
            callAdapterFactories = newAdapters
        )
        return this
    }

    fun build(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(config.connectTimeout, TimeUnit.MILLISECONDS)
            readTimeout(config.readTimeout, TimeUnit.MILLISECONDS)
            writeTimeout(config.writeTimeout, TimeUnit.MILLISECONDS)

            config.interceptors.forEach { interceptor ->
                addInterceptor(interceptor)
            }

        }.build()

        return Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .client(okHttpClient)
            .apply {
                config.converterFactories.forEach { factory ->
                    addConverterFactory(factory)
                }
                config.callAdapterFactories.forEach { factory ->
                    addCallAdapterFactory(factory)
                }
            }
            .build()
    }

    inline fun <reified T> createService(): T {
        return build().create(T::class.java)
    }
}