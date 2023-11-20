package com.example.projecthilt.stateFlow
sealed class StateFlow {
    object Loading: StateFlow()
    data class Success<T>(val  data: T): StateFlow()
    data class Error(val errorMessage: Throwable): StateFlow()
    object Empty: StateFlow()
}