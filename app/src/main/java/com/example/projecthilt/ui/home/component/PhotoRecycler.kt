package com.example.projecthilt.ui.home.component

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.projecthilt.ui.home.model.Photo

@Composable
fun PhotoRecycler(
    modifier: Modifier,
    photoList: List<Photo?>,
    onClick: (success: Photo) -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(photoList.size) {
                photoList[it]?.let { it1 -> ItemPhoto(it1, onClick) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemPhoto(photoJson: Photo, onClick: (success: Photo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.large,
        onClick = {
            onClick(photoJson)
        }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (image, title) = createRefs()
            AsyncImage(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(8.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentDescription = photoJson.title,
                model = photoJson.thumbnailUrl
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        width = Dimension.fillToConstraints
                        top.linkTo(image.top)
                        bottom.linkTo(image.bottom)
                        start.linkTo(image.end)
                        end.linkTo(parent.end)
                    }
                    .padding(0.dp, 8.dp),
                text = photoJson.title,
            )
        }
    }
}