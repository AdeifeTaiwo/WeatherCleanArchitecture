package com.example.weathercleanarchitecture.utility

sealed class Resource<T> (
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?) : Resource<T>(null, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}


