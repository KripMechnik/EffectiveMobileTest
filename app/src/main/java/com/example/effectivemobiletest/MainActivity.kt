package com.example.effectivemobiletest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.core.di.utils.getComponent
import com.example.effectivemobiletest.di.AppComponent
import com.example.effectivemobiletest.navigation.MainRouterImpl
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainRouterImpl: MainRouterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        getComponent<AppComponent>().inject(this)
        mainRouterImpl.setActivity(this)
        setContentView(R.layout.activity_main_layout)
    }
}