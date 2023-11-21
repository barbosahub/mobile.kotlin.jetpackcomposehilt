package com.example.projecthilt.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.projecthilt.ui.home.model.Photo

@Composable
fun PhotoRecycler(
    photoList: List<Photo>,
    onClick: (success: Photo) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(photoList.size) {
            ItemPhoto(photoList[it], onClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemPhoto(photo: Photo, onClick: (success: Photo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.large,
        onClick = {
            onClick(photo)
        }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentDescription = photo.title,
                    model = photo.thumbnailUrl
                )

                Text(
                    modifier = Modifier.padding(0.dp, 8.dp),
                    text = photo.title
                )
            }
        }
    }
}