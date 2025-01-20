package com.duccionarbone.cleanarchitectured.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhoto
import com.duccionarbone.presentation.base.UiState
import com.duccionarbone.presentation.details.DetailScreen
import com.duccionarbone.presentation.home.HomeViewModel
import kotlinx.serialization.json.Json
import androidx.compose.animation.SharedTransitionLayout
import com.duccionarbone.presentation.SharedTransitionContent.SharedTransitionComponent
import com.duccionarbone.presentation.home.HomeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(paddingValues: PaddingValues, homeViewModel: HomeViewModel) {

    val navController = rememberNavController()

    val uiState by homeViewModel.uiState.collectAsState()
    val marsPhotos by homeViewModel.marsPhotos.collectAsState()
    val nasaPhotos by homeViewModel.nasaPhotos.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getMarsPhotos("curiosity", 1000, "dixfIgjWWMjCmNjqpYTkHvZTCelNKPvPC6nih2Wq")
        homeViewModel.getNasaPhotos("mars surface", "image")
    }


        NavHost(navController, startDestination = "home") {
            composable("home") {
                if (uiState == UiState.Success) {
                    HomeScreen(paddingValues, navController, nasaPhotos)
                } else {
                    // Show a loading screen or some other UI while waiting for the photos to load
                    //LoadingScreen()
                }
            }
            composable("detail/{nasaPhotoJson}") { backStackEntry ->
                val serializedPhoto =
                    backStackEntry.arguments?.getString("nasaPhotoJson")?.replace("$$$", "/")
                val nasaPhoto: NasaPhoto? = serializedPhoto?.let {
                    Json.decodeFromString<NasaPhoto>(it)
                }
                if (nasaPhoto != null) {
                    //DetailScreen(paddingValues, nasaPhoto)
                }
            }
        }

}