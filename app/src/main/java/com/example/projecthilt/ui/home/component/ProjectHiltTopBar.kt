package com.example.projecthilt.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectHiltTopBar() {

    CenterAlignedTopAppBar(
        title = {
            Text("TopAppBar")
        },
        colors =
        TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.LightGray
        ),
    )


}

