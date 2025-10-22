package com.example.home.ui.main

import android.util.Log
import com.example.core.utils.Resource
import com.example.home.domain.GetCoursesUseCase
import com.example.home.domain.entity.CourseEntity
import com.example.mvi.ViewModelStore
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModelStore<MainScreenState, MainScreenSideEffect>(MainScreenState(null)) {

    init {
        intent {
            getCoursesUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> handleError(result)
                    is Resource.Loading -> reduce {
                        this.copy(courses = null, loading = true)
                    }
                    is Resource.Success -> reduce {
                        this.copy(courses = result.data, loading = false)
                    }
                }
            }
        }
    }

    private fun <T> handleError(error: Resource.Error<T>){
        Log.e("GetCoursesError", error.errorMessage ?: "Unknown error")
    }

    override fun sideEffectProducer(state: MainScreenState): MainScreenSideEffect {
        return MainScreenSideEffect.Idle
    }
}

data class MainScreenState(
    val courses: List<CourseEntity>?,
    val loading: Boolean = false
)

sealed class MainScreenSideEffect {
    data object Idle : MainScreenSideEffect()
}