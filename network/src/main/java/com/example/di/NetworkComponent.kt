package com.example.di

import com.example.network.api.CoursesApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {

    val coursesApi: CoursesApi

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponent
    }
}