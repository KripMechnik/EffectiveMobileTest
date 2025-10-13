package com.example.core.di.utils

import com.example.core.di.AppInjector

inline fun <reified Component : Any> getComponent(): Component {
    return AppInjector.getComponent(Component::class)
}

inline fun <reified Component : Any> deleteComponent() {
    AppInjector.deleteComponent(Component::class)
}