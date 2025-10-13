package com.example.effectivemobiletest.di

import android.content.Context
import com.example.auth.di.AuthDependencies
import com.example.core.di.CoreDependencies
import com.example.effectivemobiletest.MainActivity
import com.example.effectivemobiletest.navigation.MainRouterImpl
import com.example.home.di.HomeDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent: CoreDependencies, AuthDependencies, HomeDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun mainRouter(): MainRouterImpl
}