package com.example.core.utils

sealed class Resource <T> (val data: T? = null, val errorCode: Int? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>: Resource<T>()
    class Error<T>(errorCode: Int?, errorMessage: String?): Resource<T>(errorCode = errorCode, errorMessage = errorMessage)
}