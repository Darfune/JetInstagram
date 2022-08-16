package com.example.jetinstagram.widgets

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.jetinstagram.firebase.FirebaseHandlerViewModel

@Composable
fun NotificationMessage(viewModel: FirebaseHandlerViewModel) {
    val notificationState = viewModel.popupNotification.value
    val notificationMessage = notificationState?.getContentOrNull()
    if (notificationMessage != null){
        Toast.makeText(LocalContext.current, notificationMessage, Toast.LENGTH_LONG).show()
    }
}