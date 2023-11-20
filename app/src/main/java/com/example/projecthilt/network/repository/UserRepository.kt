package com.example.projecthilt.network.repository

import com.example.projecthilt.network.API
import com.example.projecthilt.ui.home.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
class UserRepository
@Inject
constructor(private val api: API){
    fun getPhotos(): Flow<List<Photo>> = flow {
        emit(api.getPhotos())
    }.flowOn(Dispatchers.IO)

}