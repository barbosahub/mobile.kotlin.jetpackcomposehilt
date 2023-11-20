package com.example.projecthilt.ui.home.model

import com.squareup.moshi.Json
import org.json.JSONObject
import java.io.Serializable
//data class Photo(
//    var albumId: Int,
//    var id: Int,
//    var title: String,
//    var url: String,
//    var thumbnailUrl: String
//
//)

data class Photo(
    @field:Json(name = "albumId") val albumId: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "thumbnailUrl") val thumbnailUrl: String)