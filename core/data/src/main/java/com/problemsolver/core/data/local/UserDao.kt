package com.problemsolver.core.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.problemsolver.core.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getUsersPagination(): PagingSource<Int, User>

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM users")
    suspend fun clearAll()

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): User?
}