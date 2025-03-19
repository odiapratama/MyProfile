package com.problemsolver.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.problemsolver.core.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}