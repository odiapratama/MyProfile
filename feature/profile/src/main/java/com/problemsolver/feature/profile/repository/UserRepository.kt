package com.problemsolver.feature.profile.repository

import androidx.paging.Pager
import com.problemsolver.core.data.model.User


interface UserRepository {
    suspend fun getUsers(): List<User>
    fun getUserPagination(): Pager<Int, User>
    suspend fun getUserById(id: String): User?
}