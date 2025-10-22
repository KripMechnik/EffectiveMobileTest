package com.example.core.di

import android.content.Context
import com.example.core.navigation.AuthRouter
import com.example.core.navigation.MainScreenRouter

interface CoreDependencies {

    val context: Context

    val activityProvider: ActivityProvider

    val authRouter: AuthRouter

    val mainScreenRouter: MainScreenRouter
}