package com.example.projecthilt.ui.home.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.magnifier
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.projecthilt.stateFlow.StateFlow
import com.example.projecthilt.ui.home.component.PhotoRecycler
import com.example.projecthilt.ui.home.component.ProjectHiltTopBar
import com.example.projecthilt.ui.home.model.Photo
import com.example.projecthilt.ui.theme.ProjectHiltTheme
import com.example.projecthilt.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        when (val result = viewModel.response.value) {
            is StateFlow.Loading -> CircularProgressIndicator()
            is StateFlow.Success<*> -> InitPhotos(result.data as List<Photo?>)
            is StateFlow.Error -> Text(text = "${result.errorMessage}")
            StateFlow.Empty -> {}
        }
    }

    @Composable
    fun Body() {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (appBar, body) = createRefs()

            ProjectHiltTopBar(Modifier
                .constrainAs(appBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
                .height(56.dp))

            InitRecyclerView(
                Modifier
                    .constrainAs(body) {
                        top.linkTo(appBar.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxSize(),
                viewModel.listPhoto
            )
        }
    }

    @Composable
    fun InitPhotos(photo: List<Photo?>) {
        viewModel.listPhoto = photo

        Body()
    }

    @Composable
    fun InitRecyclerView(
        modifier: Modifier,
        photoList: List<Photo?>
    ) {
        PhotoRecycler(
            modifier = modifier,
            photoList = photoList
        ) { photo ->
            TODO()
        }
    }
}