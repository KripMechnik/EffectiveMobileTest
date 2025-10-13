package com.example.core.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class],
    dependencies = [CoreDependencies::class]
)
interface CoreComponent {

    val activityProvider: ActivityProvider

    @Component.Factory
    interface Factory {
        fun create(
            coreDependencies: CoreDependencies
        ): CoreComponent
    }
}