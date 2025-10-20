package com.example.home.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.entity.CourseEntity
import com.example.home.domain.GetCoursesUseCase
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(MainViewModelState(null))
    val state = _state.asStateFlow()

    init {
        getCoursesUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> handleError(result)
                is Resource.Loading -> _state.update {
                    MainViewModelState(courses = null, loading = true)
                }
                is Resource.Success -> _state.update {
                    MainViewModelState(courses = result.data, loading = false)
                }
            }
            _state.update {
                MainViewModelState(result.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun <T> handleError(error: Resource.Error<T>){
        Log.e("GetCoursesError", error.errorMessage ?: "Unknown error")
    }
}

data class MainViewModelState(
    val courses: List<CourseEntity>?,
    val loading: Boolean = false
)