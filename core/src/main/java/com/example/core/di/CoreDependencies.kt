package com.example.core.di

import android.content.Context

interface CoreDependencies {

    val context: Context

    val activityProvider: ActivityProvider
}