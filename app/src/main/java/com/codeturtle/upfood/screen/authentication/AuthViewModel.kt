package com.codeturtle.upfood.screen.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeturtle.upfood.data.AuthRepository
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

    private val _signUpFLow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFLow: StateFlow<Resource<FirebaseUser>?> = _signUpFLow

    val currentUser: FirebaseUser?
        get() = authRepository.currentUser

    init {
        if (authRepository.currentUser != null) {
            _loginFLow.value = Resource.Success(authRepository.currentUser!!)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFLow.value = Resource.Loading
        val result = authRepository.login(email, password)
        _loginFLow.value = result
    }

    fun signUp(name: String, email: String, password: String) = viewModelScope.launch {
        _signUpFLow.value = Resource.Loading
        val result = authRepository.singUp(name, email, password)
        _signUpFLow.value = result
    }

    fun logout() {
        authRepository.logout()
        _loginFLow.value = null
        _signUpFLow.value = null
    }
}