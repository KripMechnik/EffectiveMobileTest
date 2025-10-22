package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreComponent
import com.example.core.di.scope.HomeScope
import com.example.di.NetworkComponent
import com.example.home.ui.HomeActivity
import dagger.Component

@HomeScope
@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class, NetworkComponent::class]
)
interface HomeComponent {

    val viewModelFactory: ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            networkComponent: NetworkComponent
        ): HomeComponent
    }
    fun inject(activity: HomeActivity)
}