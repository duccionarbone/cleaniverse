package com.duccionarbone.presentation.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhoto


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    paddingValues: PaddingValues,
    nasaPhoto: NasaPhoto,
    imageLoader: ImageLoader,
    onBack: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    modifier: Modifier
) {

    with(sharedTransitionScope){
        Box(modifier=modifier.padding(top = 20.dp, bottom = 20.dp)) {

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .clickable {
                        onBack()
                    }
            ) {

                Column {
                    val imgUrl = nasaPhoto.links[0].href

                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        text = nasaPhoto.data[0].title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )


                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imgUrl)
                            .crossfade(true)
                            .build(),
                        imageLoader = imageLoader,
                        contentDescription = nasaPhoto.data[0].title,
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(key = "image"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .fillMaxWidth()
                            .height(400.dp),

                        contentScale = ContentScale.Crop,

                        )


                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        text = nasaPhoto.data[0].description,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = nasaPhoto.data[0].secondary_creator,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = nasaPhoto.data[0].description_508,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = nasaPhoto.data[0].date_created,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                }

            }
        }
    }

}