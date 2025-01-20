package com.duccionarbone.presentation.home

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhoto
import com.duccionarbone.presentation.composables.FiltersScreen
import com.duccionarbone.presentation.details.DetailScreen
import kotlinx.coroutines.delay
import okhttp3.HttpUrl

fun createImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.25) // 25% della memoria disponibile
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02) // 2% dello spazio disponibile su disco
                .build()
        }
        .respectCacheHeaders(false) // Non usare le cache headers
        .build()
}

fun convertToHttps(httpUrl: HttpUrl): HttpUrl {
    return httpUrl.newBuilder()
        .scheme("https")
        .build()
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    nasaPhotos: List<NasaPhoto>
) {
    if (nasaPhotos.isNotEmpty()) {

        val imageLoader = createImageLoader(LocalContext.current)

        FiltersScreen()

        LazyColumn {
            items(nasaPhotos) { nasaPhoto ->
                var showDetails by remember {
                    mutableStateOf(false)
                }
                SharedTransitionScope { modifier ->
                    SharedTransitionLayout {
                        AnimatedContent(
                            showDetails,
                            label = "basic_transition"
                        ) { targetState ->
                            if (!targetState) {
                                NasaCard(
                                    onShowDetails = {
                                        showDetails = true
                                    },
                                    nasaPhoto = nasaPhoto,
                                    imageLoader = imageLoader,
                                    navController = navController,
                                    context = LocalContext.current,
                                    animatedVisibilityScope = this@AnimatedContent,
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    modifier = modifier
                                )
                            } else {
                                DetailScreen(
                                    paddingValues = PaddingValues(0.dp),
                                    nasaPhoto = nasaPhoto,
                                    imageLoader = imageLoader,
                                    onBack = {
                                        showDetails = false
                                    },
                                    animatedVisibilityScope = this@AnimatedContent,
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    modifier = modifier
                                )
                            }
                        }
                    }
                }
            }
        }

        //FiltersScreen()

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NasaCard(nasaPhoto: NasaPhoto, onShowDetails: () -> Unit, imageLoader: ImageLoader, navController: NavHostController, context: Context, animatedVisibilityScope: AnimatedVisibilityScope, sharedTransitionScope: SharedTransitionScope, modifier: Modifier) {

    val httpsUrl = nasaPhoto.links[0].href

    LaunchedEffect(Unit) {
        // Simulate loading delay
        delay(200)
    }

    with(sharedTransitionScope){
        Box(modifier = modifier.height(200.dp).clickable {
//                   val nasaPhotoJson = Json.encodeToString(nasaPhoto).replace("/", "$$$")
//                   navController.navigate("detail/${nasaPhotoJson}")
            onShowDetails()
        }) {


//
//            Text(
//                text = nasaPhoto.data[0].title,
//                style = MaterialTheme.typography.titleMedium,
//                color = Color.White,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                overflow = TextOverflow.Ellipsis,
//                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
//                maxLines = 1
//            )


                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(httpsUrl)
                            .crossfade(false)
                            .build(),
                        imageLoader = imageLoader,
                        contentDescription = nasaPhoto.data[0].title,
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(key = "image"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .fillMaxWidth()
                            .height(200.dp)
//                        .clip(CircleShape),
                        ,
                        contentScale = ContentScale.Crop
                    )
                }


    }
}