package com.example.effectivemobiletest

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.auth.di.DaggerAuthComponent
import com.example.core.di.CoreDependencies
import com.example.core.di.AppInjector
import com.example.core.di.AppInjectorStore
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.di.DaggerNetworkComponent
import com.example.di.NetworkComponent
import com.example.effectivemobiletest.di.AppComponent
import com.example.effectivemobiletest.di.DaggerAppComponent
import com.example.home.di.DaggerHomeComponent
import com.example.home.di.HomeComponent
import kotlin.reflect.KClass

class App : Application(), AppInjector {
    private val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .context(this)
        .build()

    private val coreComponent: CoreComponent = DaggerCoreComponent
        .factory()
        .create(
            coreDependencies = appComponent
        )

    private val networkComponent: NetworkComponent = DaggerNetworkComponent
        .factory()
        .create()

    private var authComponent: AuthComponent? = null

    private var homeComponent: HomeComponent? = null

    override fun onCreate() {
        super.onCreate()
        AppInjectorStore.appInjector = this
    }

    override fun <Component : Any> getComponent(componentClass: KClass<Component>): Component {
        return when(componentClass){
            AppComponent::class -> this.appComponent as Component
            CoreDependencies::class -> this.appComponent as Component
            CoreComponent::class -> this.coreComponent as Component
            NetworkComponent::class -> this.networkComponent as Component
            AuthComponent::class -> {
                this.authComponent?.let { component ->
                    component as Component
                } ?: DaggerAuthComponent
                    .factory()
                    .create(
                        coreComponent = coreComponent
                    ) as Component
            }
            HomeComponent::class -> {
                this.homeComponent?.let { component ->
                    component as Component
                } ?: DaggerHomeComponent
                    .factory()
                    .create(
                        coreComponent = coreComponent,
                        networkComponent = networkComponent
                    ) as Component
            }
            else -> throw IllegalArgumentException("Such api ${componentClass::class.simpleName} not found")
        }
    }

    override fun <Component : Any> deleteComponent(componentClass: KClass<Component>) {
        when (componentClass){
            AuthComponent::class -> authComponent = null
        }
    }
}