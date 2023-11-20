package com.example.projecthilt.network

import com.example.projecthilt.ui.home.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("photos")
    suspend fun getPhotos(
    ): List<Photo>

    @GET("albums?")
    suspend fun getAlbumsById(
        @Query("id") id : Int
    ): retrofit2.Response<Map<String,Any>>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}