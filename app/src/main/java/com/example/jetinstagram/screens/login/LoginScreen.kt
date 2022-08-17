package com.example.jetinstagram.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetinstagram.R
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel
import com.example.jetinstagram.navigation.JetInstagramScreens
import com.example.jetinstagram.widgets.CommonProgressSpinner
import com.example.jetinstagram.widgets.navigateTo

@Composable
fun LoginScreen(navController: NavController, viewModel: FirebaseHandlerViewModel) {

    val usernameState = remember {
        mutableStateOf(TextFieldValue())
    }


    val passwordState = remember {
        mutableStateOf(TextFieldValue())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.ic_instagram_logo),
                contentDescription = "Instagram Logo",
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Signup",
                modifier = Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(
                value = usernameState.value,
                onValueChange = { usernameState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Username") })
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {

                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Sign Up")
            }
            Text(text = "New user? Sign up now ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, JetInstagramScreens.SignupScreen)
                    })
        }
        val isLoading = viewModel.inProgress.value
        if (isLoading){
            CommonProgressSpinner()
        }
    }
}