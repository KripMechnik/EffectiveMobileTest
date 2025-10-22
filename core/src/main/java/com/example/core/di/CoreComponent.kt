package com.example.core.di

import com.example.core.navigation.AuthRouter
import com.example.core.navigation.MainScreenRouter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class],
    dependencies = [CoreDependencies::class]
)
interface CoreComponent {

    val activityProvider: ActivityProvider

    val authRouter: AuthRouter

    val mainScreenRouter: MainScreenRouter

    @Component.Factory
    interface Factory {
        fun create(
            coreDependencies: CoreDependencies
        ): CoreComponent
    }
}