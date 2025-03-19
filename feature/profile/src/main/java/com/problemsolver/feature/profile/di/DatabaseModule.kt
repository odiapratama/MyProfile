package com.problemsolver.feature.profile.di

import android.app.Application
import androidx.room.Room
import com.problemsolver.core.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application, UserDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(userDatabase: UserDatabase) = userDatabase.userDao()
}