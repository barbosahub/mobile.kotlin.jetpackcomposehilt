package com.example.projecthilt.ui.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projecthilt.stateFlow.StateFlow
import com.example.projecthilt.ui.home.component.PhotoRecycler
import com.example.projecthilt.ui.home.component.ProjectHiltTopBar
import com.example.projecthilt.ui.home.model.Photo
import com.example.projecthilt.ui.theme.ProjectHiltTheme
import com.example.projecthilt.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectHiltTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetPhotos(viewModel = viewModel)

                    Body()
                }
            }
        }
    }

    @Composable
    @Suppress("UNCHECKED_CAST")
    fun GetPhotos(viewModel: UserViewModel) {
        when (val result = viewModel.photos.value) {
            is StateFlow.Loading -> CircularProgressIndicator()
            is StateFlow.Success<*> -> InitPhotos(result.data as List<Photo>)
            is StateFlow.Error -> Text(text = "${result.errorMessage}")
            StateFlow.Empty -> {}
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Body() {
        Scaffold(
            topBar = { ProjectHiltTopBar() }
        ) {
            Column(modifier = Modifier.padding(it)) {
                InitRecyclerView(photoList = viewModel.listPhoto.value)
            }
        }
    }

    @Composable
    fun InitPhotos(photo: List<Photo>) {
        viewModel.listPhoto.value = photo

        Body()
    }

    @Composable
    fun InitRecyclerView(photoList: List<Photo>) {
        PhotoRecycler(
            photoList = photoList
        ) {
            Intent(this, PhotoActivity::class.java).apply {
                putExtra("photo",it)
                startActivity(this)
            }
        }
    }
}