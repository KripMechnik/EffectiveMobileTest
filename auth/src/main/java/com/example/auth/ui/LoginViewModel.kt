package com.example.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.api.CoursesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableStateFlow(LoginViewModelState(false, false))
    val state = _state.asStateFlow()

    fun checkEmail(email: String) {
        _state.update {
            it.copy(emailStatus = email.isValidEmail())
        }
    }

    fun checkPassword(password: String) {
        _state.update {
            it.copy(passwordStatus = password.isNotBlank())
        }
    }

    private fun String.isValidEmail(): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()
        return this.matches(emailRegex)
    }
}

data class LoginViewModelState(
    val emailStatus: Boolean,
    val passwordStatus: Boolean
)