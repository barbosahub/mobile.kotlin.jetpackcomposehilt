package com.example.projecthilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projecthilt.stateFlow.StateFlow
import com.example.projecthilt.ui.theme.ProjectHiltTheme
import com.example.projecthilt.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectHiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//  val viewModel = hiltViewModel<UserViewModel>()

                    GetPhotos(viewModel = viewModel)

                }
            }
        }
    }
}

@Composable fun GetPhotos(viewModel: UserViewModel) {
    when (val result = viewModel.response.value) {
        is StateFlow.Loading -> CircularProgressIndicator()
        is StateFlow.Success<*> -> InitPhotos(result.data)
        is StateFlow.Error -> Text(text = "${result.errorMessage}")
        StateFlow.Empty -> {}
    }
}

@Composable fun InitPhotos(photo: Any?) {
    val teste = photo

}