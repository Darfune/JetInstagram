package com.example.jetinstagram.firebase

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetinstagram.data.Event
import com.example.jetinstagram.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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
    val knownUserData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)

    init {
//        auth.signOut()
        val currentUser = auth.currentUser
        signedIn.value = currentUser != null
        currentUser?.uid?.let { uid ->
            getUserData(uid)
        }
    }

    fun onSignup(username: String, email: String, password: String) {
        if (username.isEmpty()) {
            handleException(customMessage = "Please enter your username")
            return
        } else if (email.isEmpty()) {
            handleException(customMessage = "Please enter your email")
            return
        } else if (password.isEmpty()) {
            handleException(customMessage = "Please enter your password")
            return
        }
        inProgress.value = true

        database.collection(USERS).whereEqualTo("username", username).get()
            .addOnSuccessListener { entries ->
                if (entries.size() > 0) {
                    handleException(customMessage = "Username already exists")
                    inProgress.value = false
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                signedIn.value = true
                                createOrUpdateProfile(username = username)
                            } else {
                                handleException(task.exception, "Signup Failed")
                            }
                            inProgress.value = false
                        }
                }
            }
            .addOnFailureListener { }
    }

    private fun createOrUpdateProfile(
        name: String? = null,
        username: String,
        bio: String? = null,
        imageUrl: String? = null
    ) {
        val uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: knownUserData.value?.name,
            username = username ?: knownUserData.value?.username,
            bio = bio ?: knownUserData.value?.bio,
            imageUrl = imageUrl ?: knownUserData.value?.imageUrl,
            following = knownUserData.value?.following
        )

        uid?.let {
            inProgress.value = true
            database.collection(USERS).document(uid).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        it.reference.update(userData.dataToMap())
                            .addOnSuccessListener {
                                this.knownUserData.value = userData
                                inProgress.value = false
                            }
                            .addOnFailureListener {
                                handleException(it, "Cannot update user")
                                inProgress.value = false
                            }
                    } else {
                        database.collection(USERS).document(uid).set(userData)
                        getUserData(uid)
                        inProgress.value = false
                    }
                }
                .addOnFailureListener { exception ->
                    handleException(exception, "Cannot create user")
                    inProgress.value = false
                }
        }
    }

    private fun getUserData(uid: String) {
        inProgress.value = true
        database.collection(USERS).document(uid).get()
            .addOnSuccessListener {
                val user = it.toObject<UserData>()
                knownUserData.value = user
                inProgress.value = false
            }
            .addOnFailureListener { exception ->
                handleException(exception, "Cannot retrieve user data")
                inProgress.value = false
            }
    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMessage = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMessage else "$customMessage: $errorMessage"
        popupNotification.value = Event(message)
    }

    fun onLogin(email: String, password: String) {
        if (email.isEmpty()) {
            handleException(customMessage = "Please enter your email")
            return
        } else if (password.isEmpty()) {
            handleException(customMessage = "Please enter your password")
            return
        }
        inProgress.value = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                    inProgress.value = false
                    auth.currentUser?.uid?.let { uid ->
                        handleException(customMessage = "Login was Successful")
                        getUserData(uid)
                    }
                } else {
                    handleException(task.exception, "Unable to login")
                }
            }
            .addOnFailureListener { exception ->
                handleException(exception, "Unable to login")
                inProgress.value = false
            }
    }

}