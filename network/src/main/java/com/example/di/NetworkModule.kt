package com.example.di

import com.example.network.api.CoursesApi
import com.example.network.builder.RetrofitClientBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitClientBuilder.create().build()
    }

    @Provides
    fun provideCoursesApi(retrofit: Retrofit): CoursesApi {
        return retrofit.create(CoursesApi::class.java)
    }
}