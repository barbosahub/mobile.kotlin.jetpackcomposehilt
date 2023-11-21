package com.example.projecthilt.ui.home.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.projecthilt.ui.home.component.ProjectHiltTopBar
import com.example.projecthilt.ui.home.model.Photo
import com.example.projecthilt.ui.theme.ProjectHiltTheme
import com.example.projecthilt.util.serializable
import com.squareup.moshi.Json

class PhotoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectHiltTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Body() {
        val photo = intent.serializable<Photo>("photo")

        Scaffold(
            topBar = { ProjectHiltTopBar() }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (photo != null) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentDescription = photo.title,
                        model = photo.url
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Id:${photo.id}")
                        Text(text = "Album Id:${photo.albumId}")
                        Text(text = "Title:${photo.title}")
                        Text(text = "Url:${photo.url}")
                        Text(text = "Thumbnail:${photo.thumbnailUrl}")
                    }

                }
            }
        }
    }
}