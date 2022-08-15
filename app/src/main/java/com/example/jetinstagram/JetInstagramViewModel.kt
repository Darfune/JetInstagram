package com.example.jetinstagram

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JetInstagramViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {
}