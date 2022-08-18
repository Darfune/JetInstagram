package com.example.jetinstagram.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel
import com.example.jetinstagram.menu.BottomNavigationItem
import com.example.jetinstagram.menu.BottomNavigationMenu

@Composable
fun SearchScreen(navController: NavController, viewModel: FirebaseHandlerViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Search Screen")
        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SEARCH,
            navController = navController
        )
    }
}