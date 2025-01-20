package com.duccionarbone.presentation.home

import android.content.Context
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhoto
import com.duccionarbone.presentation.base.UiState
import com.duccionarbone.presentation.composables.FiltersScreen
import com.duccionarbone.presentation.composables.LoadingScreen
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl

fun convertToHttps(httpUrl: HttpUrl): HttpUrl {
    return httpUrl.newBuilder()
        .scheme("https")
        .build()
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(paddingValues: PaddingValues, uiState: UiState, navController: NavHostController, imageLoader: ImageLoader, animatedVisibilityScope: AnimatedVisibilityScope){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ){
        FiltersScreen()
        if (uiState == UiState.Success) {
            ListScreen(null, navController, imageLoader=imageLoader, animatedVisibilityScope = animatedVisibilityScope)
        } else if (uiState == UiState.Loading){
            // Show a loading screen or some other UI while waiting for the photos to load
            LoadingScreen()
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    paddingValues: PaddingValues?,
    navController: NavHostController,
    imageLoader: ImageLoader,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val nasaPhotos = homeViewModel.nasaPhotos.collectAsState().value

    if (nasaPhotos.isNotEmpty()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(nasaPhotos) { nasaPhoto ->
                NasaCard(
                    nasaPhoto = nasaPhoto,
                    imageLoader = imageLoader,
                    navController = navController,
                    context = LocalContext.current,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NasaCard(
    nasaPhoto: NasaPhoto,
    imageLoader: ImageLoader,
    navController: NavHostController,
    context: Context,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val httpsUrl = nasaPhoto.links[0].href


    Box(modifier = Modifier
        .height(200.dp)
        .clickable {
            val nasaPhotoJson = Json.encodeToString(nasaPhoto).replace("/", "$$$")
            navController.navigate("detail/${nasaPhotoJson}")
        }) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(httpsUrl)
                .crossfade(false)
                .build(),
            imageLoader = imageLoader,
            loading = {
                LoadingScreen()
            },
            contentDescription = nasaPhoto.data?.get(0)?.title,
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(key = "image{${nasaPhoto.href}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .height(200.dp)
//                        .clip(CircleShape),
            ,
            contentScale = ContentScale.Crop
        )
    }
}