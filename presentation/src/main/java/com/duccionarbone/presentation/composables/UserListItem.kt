package com.duccionarbone.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.duccionarbone.cleanarchitectured.domain.entities.GitHub.User

@Composable
fun UsersListItem(
    user: User,
    onItemClick: (User) -> Unit
) {

    Text(
        text = user.userId,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}