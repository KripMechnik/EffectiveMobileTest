package com.example.core.di

import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

interface ActivityProvider {

    val activityWeakRef: WeakReference<AppCompatActivity>

    fun setActivity(activity: AppCompatActivity)

    fun clear()
}