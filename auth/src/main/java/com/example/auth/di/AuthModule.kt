package com.example.auth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auth.ui.LoginViewModel
import com.example.core.di.ViewModelKey
import com.example.core.di.scope.AuthScope
import com.example.core.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AuthModule.Bind::class])
object AuthModule {

    @Module
    interface Bind {

        @Binds
        @AuthScope
        fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @AuthScope
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel
    }
}