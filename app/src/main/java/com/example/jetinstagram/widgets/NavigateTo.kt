package com.example.jetinstagram.widgets

import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavController
import androidx.navigation.PopUpToBuilder
import com.example.jetinstagram.navigation.JetInstagramScreens

fun navigateTo(navController: NavController, destination: JetInstagramScreens) {
    navController.navigate(destination.name) {
        popUpTo(destination.name)
        launchSingleTop = true
    }
}