package com.codeturtle.upfood.screen.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeturtle.upfood.data.repositories.AuthRepository
import com.codeturtle.upfood.data.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _loginFLow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFLow

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    private val _forgotPasswordFlow = MutableStateFlow<Resource<Boolean>?>(null)
    val forgotPasswordFLow: StateFlow<Resource<Boolean>?> = _forgotPasswordFlow

    val currentUser: FirebaseUser?
        get() = authRepository.currentUser

    init {
        if(authRepository.currentUser?.isEmailVerified == true){
            if (authRepository.currentUser != null) {
                _loginFLow.value = Resource.Success(authRepository.currentUser!!)
            }
        }

    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFLow.value = Resource.Loading
        val result = authRepository.login(email, password)
        _loginFLow.value = result
    }

    fun signUp(name: String, email: String, password: String) = viewModelScope.launch {
        _signUpFlow.value = Resource.Loading
        val result = authRepository.singUp(name, email, password)
        _signUpFlow.value = result
    }

    fun forgotPassword(email: String) = viewModelScope.launch {
        _forgotPasswordFlow.value = Resource.Loading
        val result = authRepository.forgotPassword(email)
        _forgotPasswordFlow.value = result
    }

    fun logout() {
        authRepository.logout()
        _loginFLow.value = null
        _signUpFlow.value = null
        _forgotPasswordFlow.value = null
    }
}