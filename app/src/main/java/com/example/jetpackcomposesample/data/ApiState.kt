package com.example.jetpackcomposesample.data

sealed class ApiState<out R> {
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error(val exception: Exception) : ApiState<Nothing>()
}

fun <T> ApiState<T>.successOr(fallback: T): T {
    return (this as? ApiState.Success<T>)?.data ?: fallback
}
