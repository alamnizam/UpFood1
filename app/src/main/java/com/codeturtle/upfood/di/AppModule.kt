package com.codeturtle.upfood.di

import com.codeturtle.upfood.data.repositories.AuthRepository
import com.codeturtle.upfood.data.repositories.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository = authRepositoryImpl
}