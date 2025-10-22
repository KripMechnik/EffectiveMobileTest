package com.example.effectivemobiletest.di

import com.example.core.navigation.AuthRouter
import com.example.core.di.ActivityProvider
import com.example.core.navigation.MainScreenRouter
import com.example.core.navigation.main.MainRouter
import com.example.effectivemobiletest.navigation.MainRouterImpl
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideAuthRouter(mainRouterImpl: MainRouterImpl): AuthRouter {
        return mainRouterImpl
    }

    @Provides
    fun provideMainScreenRouter(mainRouterImpl: MainRouterImpl): MainScreenRouter {
        return mainRouterImpl
    }

    @Provides
    fun provideActivityProvider(mainRouterImpl: MainRouterImpl): ActivityProvider {
        return mainRouterImpl
    }

    @Provides
    fun provideMainRouter(mainRouterImpl: MainRouterImpl): MainRouter {
        return mainRouterImpl
    }
}