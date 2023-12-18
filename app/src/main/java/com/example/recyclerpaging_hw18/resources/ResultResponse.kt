package com.example.recyclerpaging_hw18.resources

sealed class ResultResponse<T> {
    data class Success<T>(val users: T) : ResultResponse<T>()
    data class Error<T>(val error: String) : ResultResponse<T>()
}