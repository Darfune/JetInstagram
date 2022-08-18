package com.example.jetinstagram.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel
import com.example.jetinstagram.screens.authetication.SignupScreen
import com.example.jetinstagram.screens.feed.FeedScreen
import com.example.jetinstagram.screens.login.LoginScreen
import com.example.jetinstagram.screens.myposts.MyPostScreen
import com.example.jetinstagram.screens.search.SearchScreen
import com.example.jetinstagram.widgets.NotificationMessage

@Composable
fun JetInstagramNavigation() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<FirebaseHandlerViewModel>()
    NotificationMessage(viewModel = viewModel)
    NavHost(navController = navController, startDestination = JetInstagramScreens.SignupScreen.name) {
        composable(JetInstagramScreens.SignupScreen.name) {
            SignupScreen(navController = navController, viewModel = viewModel)
        }
        composable(JetInstagramScreens.LoginScreen.name) {
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable(JetInstagramScreens.FeedScreen.name) {
            FeedScreen(navController = navController, viewModel = viewModel)
        }
        composable(JetInstagramScreens.SearchScreen.name) {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
        composable(JetInstagramScreens.MyPostsScreen.name) {
            MyPostScreen(navController = navController, viewModel = viewModel)
        }
    }
}