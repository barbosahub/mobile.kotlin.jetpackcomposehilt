package com.example.projecthilt.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecthilt.network.repository.UserRepository
import com.example.projecthilt.stateFlow.StateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val response: MutableState<StateFlow> = mutableStateOf(StateFlow.Empty)
    init {
        getPhotos()
    }
    fun getPhotos() =
        viewModelScope.launch {
            userRepository.getPhotos().onStart {
                response.value= StateFlow.Loading
            }.catch {
                response.value= StateFlow.Error(it)
            }.collect {
                response.value=StateFlow.Success<Any>(it)
            }
        }
}