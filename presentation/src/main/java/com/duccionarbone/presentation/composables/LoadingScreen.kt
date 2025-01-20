package com.duccionarbone.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.duccionarbone.cleanarchitectured.presentation.R


@Composable
fun LoadingScreen() {
    // Show a loading screen or some other UI while waiting for the photos to load
    // For example:
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(), contentAlignment = Alignment.Center ){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun LoadingImage(){
 Icon(
     imageVector = ImageVector.vectorResource(R.drawable.landscape_placeholder_svgrepo_com),
     contentDescription = null,
     modifier = Modifier.size(128.dp),
     tint = Color.Gray
 )
}