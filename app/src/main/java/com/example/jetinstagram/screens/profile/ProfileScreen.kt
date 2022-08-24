package com.example.jetinstagram.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel
import com.example.jetinstagram.navigation.JetInstagramScreens
import com.example.jetinstagram.widgets.CommonDivider
import com.example.jetinstagram.widgets.CommonProgressSpinner
import com.example.jetinstagram.widgets.navigateTo

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FirebaseHandlerViewModel
) {
    val isLoading = viewModel.inProgress.value
    if (isLoading) {
        CommonProgressSpinner()
    } else {
        val userData = viewModel.knownUserData.value
        var name by rememberSaveable {
            mutableStateOf(userData?.name ?: "")
        }
        var username by rememberSaveable {
            mutableStateOf(userData?.username ?: "")
        }
        var bio by rememberSaveable {
            mutableStateOf(userData?.bio ?: "")
        }

        ProfileContent(
            viewModel = viewModel,
            name = name,
            username = username,
            bio = bio,
            onNameChange = { name = it },
            onUsernameChange = { username = it },
            onBioChange = { bio = it },
            onSave = { },
            onBack = { navigateTo(navController, JetInstagramScreens.MyPostsScreen) },
            onLogout = { }
        )
    }
}

@Composable
fun ProfileContent(
    viewModel: FirebaseHandlerViewModel,
    name: String,
    username: String,
    bio: String,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Back", modifier = Modifier.clickable { onBack.invoke() })
            Text(text = "Save", modifier = Modifier.clickable { onSave.invoke() })
        }

        CommonDivider()

        // User image
        Column(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        ) {

        }

        CommonDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Name", modifier = Modifier.width(100.dp))
            TextField(
                value = name,
                onValueChange = onNameChange,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Username", modifier = Modifier.width(100.dp))
            TextField(
                value = username,
                onValueChange = onUsernameChange,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Bio", modifier = Modifier.width(100.dp))
            TextField(
                value = bio,
                onValueChange = onBioChange,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black
                ),
                singleLine = false,
                modifier = Modifier.height(150.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Logout",
                modifier = Modifier.clickable { onLogout.invoke() })
        }
    }
}