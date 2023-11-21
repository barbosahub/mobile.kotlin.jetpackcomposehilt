package com.example.projecthilt.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecthilt.network.repository.UserRepository
import com.example.projecthilt.stateFlow.StateFlow
import com.example.projecthilt.ui.home.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val photos: MutableState<StateFlow> = mutableStateOf(StateFlow.Empty)
    val albums: MutableState<StateFlow> = mutableStateOf(StateFlow.Empty)

    var listPhoto = mutableStateOf<List<Photo>>(listOf())

    init {
        getPhotos()
    }

    fun getPhotos() =
        viewModelScope.launch {
            userRepository.getPhotos().onStart {
                photos.value = StateFlow.Loading
            }.catch {
                photos.value = StateFlow.Error(it)
            }.collect {
                photos.value = StateFlow.Success<Any>(it)
            }
        }
}