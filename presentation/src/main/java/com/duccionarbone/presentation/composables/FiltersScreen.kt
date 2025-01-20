package com.duccionarbone.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.duccionarbone.presentation.home.HomeViewModel

@Composable
fun FiltersScreen(){

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val radioOptions = listOf("Mars", "Moon", "Earth")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.Center
    ) {
        radioOptions.forEach { label ->
            Row(
                Modifier.padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (label == selectedOption),
                    onClick = {
                        selectedOption = label
                        homeViewModel.updateFiltersAndCallApi("$label planet")
                    }
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}