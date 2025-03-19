package com.problemsolver.feature.profile.di

import com.problemsolver.core.data.local.UserDao
import com.problemsolver.core.data.remote.UserApi
import com.problemsolver.feature.profile.repository.UserRepository
import com.problemsolver.feature.profile.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi,
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(userApi, userDao)
    }
}