package com.example.jetinstagram.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetinstagram.R
import com.example.jetinstagram.navigation.JetInstagramScreens
import com.example.jetinstagram.widgets.navigateTo

enum class BottomNavigationItem(val icon: Int, val navDestination: JetInstagramScreens) {
    FEED(R.drawable.ic_home, navDestination = JetInstagramScreens.FeedScreen),
    SEARCH(R.drawable.ic_search, navDestination = JetInstagramScreens.SearchScreen),
    POSTS(R.drawable.ic_posts, navDestination = JetInstagramScreens.MyPostsScreen)
}

@Composable
fun BottomNavigationMenu(selectedItem: BottomNavigationItem, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .padding(top = 4.dp)
            .background(Color.White)
    ) {
        for (item in BottomNavigationItem.values()) {
            Image(
                painter = painterResource(id = item.icon), contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController, item.navDestination)
                    },
                colorFilter = ColorFilter.tint(if (item == selectedItem) Color.Black else Color.Gray)
            )
        }
    }
}