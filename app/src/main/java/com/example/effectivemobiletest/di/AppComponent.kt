package com.example.effectivemobiletest.di

import android.content.Context
import com.example.core.di.CoreDependencies
import com.example.effectivemobiletest.MainActivity
import com.example.effectivemobiletest.navigation.MainRouterImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent: CoreDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun mainRouter(): MainRouterImpl
}