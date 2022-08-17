package com.example.jetinstagram.screens.feed

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel

@Composable
fun FeedScreen(navController: NavController, viewModel: FirebaseHandlerViewModel) {
    Text(text = "Feed Screen")
}