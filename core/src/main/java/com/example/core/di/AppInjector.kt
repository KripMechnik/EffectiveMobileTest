package com.example.core.di

import kotlin.properties.Delegates
import kotlin.reflect.KClass

interface AppInjector {

    fun <Component: Any> getComponent(componentClass: KClass<Component>): Component

    fun <Component: Any> deleteComponent(componentClass: KClass<Component>)

    companion object: AppInjector by AppInjectorStore.appInjector
}

object AppInjectorStore {
    var appInjector: AppInjector by Delegates.notNull()
}