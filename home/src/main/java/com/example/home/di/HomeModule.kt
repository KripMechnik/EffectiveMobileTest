package com.example.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ViewModelKey
import com.example.core.factory.ViewModelFactory
import com.example.home.data.CourseRepo
import com.example.home.data.CourseRepoImpl
import com.example.home.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [HomeModule.Bind::class])
object HomeModule {
    @Module
    interface Bind {

        @Binds
        @HomeScope
        fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @HomeScope
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun provideNotificationsViewModel(viewModel: MainViewModel): ViewModel

        @Binds
        fun provideCourseRepo(courseRepoImpl: CourseRepoImpl): CourseRepo
    }
}