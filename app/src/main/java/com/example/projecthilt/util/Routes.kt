package com.example.projecthilt.util

sealed class Routes(val id: String){
    object MainActivity:Routes("MainActivity")
}