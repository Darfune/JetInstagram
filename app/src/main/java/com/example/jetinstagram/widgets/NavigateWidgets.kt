package com.example.jetinstagram.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel
import com.example.jetinstagram.navigation.JetInstagramScreens

fun navigateTo(navController: NavController, destination: JetInstagramScreens) {
    navController.navigate(destination.name) {
        popUpTo(destination.name)
        launchSingleTop = true
    }
}

@Composable
fun CheckSignIn(navController: NavController, viewModel: FirebaseHandlerViewModel) {
    val alreadyLoggedIn = remember {
        mutableStateOf(false)
    }
    val signIn = viewModel.signedIn.value
    if (signIn && !alreadyLoggedIn.value) {
        alreadyLoggedIn.value = true
        navController.navigate(JetInstagramScreens.FeedScreen.name) {
            popUpTo(0)
        }
    }
    
}