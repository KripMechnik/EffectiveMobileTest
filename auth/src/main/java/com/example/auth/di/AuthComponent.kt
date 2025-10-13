package com.example.auth.di

import androidx.lifecycle.ViewModelProvider
import com.example.auth.ui.LoginFragment
import com.example.di.NetworkComponent
import dagger.Component

@AuthScope
@Component(
    modules = [AuthModule::class],
    dependencies = [AuthDependencies::class]
)
interface AuthComponent {

    val viewModelFactory: ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            authDependencies: AuthDependencies
        ): AuthComponent
    }
    fun inject(fragment: LoginFragment)
}