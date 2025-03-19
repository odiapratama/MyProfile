package com.problemsolver.feature.profile.di

import android.content.Context
import com.problemsolver.core.data.local.UserDao
import com.problemsolver.feature.profile.domain.UserUseCase
import com.problemsolver.feature.profile.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideUserUseCase(
        @ApplicationContext context: Context,
        userRepository: UserRepository,
        userDao: UserDao
    ): UserUseCase {
        return UserUseCase(context, userRepository, userDao)
    }
}