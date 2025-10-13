package com.example.effectivemobiletest.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.core.di.ActivityProvider
import com.example.effectivemobiletest.MainActivity
import com.example.home.ui.HomeActivity
import com.example.home.ui.main.MainFragment
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRouterImpl @Inject constructor () : MainRouter, ActivityProvider {

    override var activityWeakRef = WeakReference<AppCompatActivity>(null)

    override fun setActivity(activity: AppCompatActivity) {
        activityWeakRef = WeakReference(activity)
    }

    override fun clear() {
        activityWeakRef.clear()
    }

    override fun navigateToFavourites() {
        val activity = activityWeakRef.get() ?: return

        if (activity is HomeActivity) {

        }
    }

    override fun navigateToProfile() {

    }

    override fun navigateToHomeFromAuth() {
        val activity = activityWeakRef.get() ?: return
        clear()
        if (activity is MainActivity){
            val intent = Intent(activity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.startActivity(intent)
        }
    }

    override fun navigateToHome() {

    }

}