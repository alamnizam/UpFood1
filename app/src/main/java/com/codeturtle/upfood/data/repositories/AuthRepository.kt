package com.codeturtle.upfood.data.repositories

import com.codeturtle.upfood.data.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun singUp(name:String,email: String, password: String): Resource<FirebaseUser>
    suspend fun forgotPassword(email: String): Resource<Boolean>
    fun logout()
}