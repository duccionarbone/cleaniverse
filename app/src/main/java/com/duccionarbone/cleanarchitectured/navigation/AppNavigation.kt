package com.duccionarbone.cleanarchitectured.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.duccionarbone.cleanarchitectured.domain.entities.Nasa.NasaPhoto
import com.duccionarbone.presentation.details.DetailScreen
import com.duccionarbone.presentation.home.HomeScreen
import com.duccionarbone.presentation.home.HomeViewModel
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(paddingValues: PaddingValues, homeViewModel: HomeViewModel) {

    val navController = rememberNavController()

    val uiState by homeViewModel.uiState.collectAsState()
    //val marsPhotos by homeViewModel.marsPhotos.collectAsState()
    //val nasaPhotos by homeViewModel.nasaPhotos.collectAsStateWithLifecycle()

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

    val imageLoader = createImageLoader(LocalContext.current)

    SharedTransitionLayout {
        NavHost(navController, startDestination = "home") {

            composable("home") {
                HomeScreen(paddingValues, navController, imageLoader, this)
            }
            composable("detail/{nasaPhotoJson}") { backStackEntry ->
                val serializedPhoto = backStackEntry.arguments?.getString("nasaPhotoJson")?.replace("$$$", "/")
                val nasaPhoto: NasaPhoto? = serializedPhoto?.let {
                    try {
                        Json.decodeFromString<NasaPhoto>(it)
                    } catch (e: Exception) {
                        null
                    }
                }
                if (nasaPhoto != null) {
                    DetailScreen(paddingValues, navController, nasaPhoto, animatedVisibilityScope = this, imageLoader = imageLoader)
                }
            }
        }
    }
}