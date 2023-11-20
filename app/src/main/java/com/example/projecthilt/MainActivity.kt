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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projecthilt.stateFlow.StateFlow
import com.example.projecthilt.ui.theme.ProjectHiltTheme
import com.example.projecthilt.ui.viewModel.UserViewModel
import com.example.projecthilt.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

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


                    LaunchedEffect(Unit) {
                        viewModel.getPhotos()
                    }



                    GetPhotos(viewModel = viewModel)
                    
//                    val navController = rememberNavController()
//                    NavHost(navController, startDestination = Routes.MainActivity.id) {
//                        composable(Routes.MainActivity.id) { backStackEntry ->
//                            // Creates a ViewModel from the current BackStackEntry
//                            // Available in the androidx.hilt:hilt-navigation-compose artifact
//                            val viewModel = hiltViewModel<UserViewModel>()
//                            GetPhotos(viewModel)
//                        }
//                    }
                }
            }
        }
    }
}

@Composable fun GetPhotos(viewModel: UserViewModel) {
    when (val result = viewModel.response.value) {
        is StateFlow.Loading -> CircularProgressIndicator()
        is StateFlow.Success<*> -> InitPhotos(result.data as JSONObject)
        is StateFlow.Error -> Text(text = "${result.errorMessage}")
        StateFlow.Empty -> {}
    }
}

@Composable fun InitPhotos(photo: JSONObject) {
    val teste = photo

}