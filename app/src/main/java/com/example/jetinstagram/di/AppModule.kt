package com.example.jetinstagram.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    fun provideAuthentication(): FirebaseAuth = Firebase.auth

    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    fun provideStorage(): FirebaseStorage = Firebase.storage
}