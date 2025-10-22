package com.example.auth.di

import androidx.lifecycle.ViewModelProvider
import com.example.auth.ui.LoginFragment
import com.example.core.di.CoreComponent
import com.example.core.di.scope.AuthScope
import dagger.Component

@AuthScope
@Component(
    modules = [AuthModule::class],
    dependencies = [CoreComponent::class]
)
interface AuthComponent {

    val viewModelFactory: ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): AuthComponent
    }
    fun inject(fragment: LoginFragment)
}