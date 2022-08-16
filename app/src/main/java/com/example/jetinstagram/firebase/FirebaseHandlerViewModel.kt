package com.example.jetinstagram.firebase

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetinstagram.data.Event
import com.example.jetinstagram.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


const val USERS = "users"

@HiltViewModel
class FirebaseHandlerViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {

    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)

    fun onSignup(username: String, email: String, password: String) {
        inProgress.value = true

        database.collection(USERS).whereEqualTo("username", username).get()
            .addOnSuccessListener { entries ->
                if (entries.size() > 0) {
                    handleException(customMessage = "Username already exists")
                    inProgress.value = false
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                signedIn.value = true
                            } else {
                                handleException(task.exception, "Signup Failed")
                            }
                            inProgress.value = false
                        }
                }
            }
            .addOnFailureListener {  }
    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMessage = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMessage else "$customMessage: $errorMessage"
        popupNotification.value = Event(message)
    }

}