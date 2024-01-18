package com.codeturtle.upfood.data.repositories

import com.codeturtle.upfood.data.Resource
import com.codeturtle.upfood.data.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result =  firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e.message!!)
        }
    }

    override suspend fun singUp(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result =  firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            result?.user?.sendEmailVerification()?.await()
            Resource.Success(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e.message!!)
        }
    }

    override suspend fun forgotPassword(email: String): Resource<Boolean> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Resource.Success(true)
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e.message!!)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}