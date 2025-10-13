package com.example.home.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.di.ActivityProvider
import com.example.core.di.utils.getComponent
import com.example.home.R
import com.example.home.databinding.ActivityHomeBinding
import com.example.home.di.HomeComponent
import com.example.home.ui.favourites.FavouritesFragment
import com.example.home.ui.main.MainFragment
import com.example.home.ui.profile.ProfileFragment
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var activityProvider: ActivityProvider
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    var currentId = R.id.homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        val component = getComponent<HomeComponent>()
        component.inject(this)
        activityProvider.setActivity(this)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    when (currentId) {
                        R.id.profileFragment -> navController.navigate(R.id.action_profileFragment_to_homeFragment)
                        R.id.favouritesFragment -> navController.navigate(R.id.action_favouritesFragment_to_homeFragment)
                    }
                    currentId = R.id.homeFragment
                    true
                }
                R.id.favouritesFragment -> {
                    when (currentId) {
                        R.id.profileFragment -> navController.navigate(R.id.action_profileFragment_to_favouritesFragment)
                        R.id.homeFragment -> navController.navigate(R.id.action_homeFragment_to_favouritesFragment)
                    }
                    currentId = R.id.favouritesFragment
                    true
                }
                R.id.profileFragment -> {
                    when (currentId) {
                        R.id.homeFragment -> navController.navigate(R.id.action_homeFragment_to_profileFragment)
                        R.id.favouritesFragment -> navController.navigate(R.id.action_favouritesFragment_to_profileFragment)
                    }
                    currentId = R.id.profileFragment
                    true
                }
                else -> false
            }
        }
    }
}