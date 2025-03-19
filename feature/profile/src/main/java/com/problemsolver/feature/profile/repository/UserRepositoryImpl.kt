package com.problemsolver.feature.profile.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.problemsolver.core.data.local.UserDao
import com.problemsolver.core.data.model.User
import com.problemsolver.core.data.remote.UserApi
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUsers(): List<User> = userApi.getUser()

    override fun getUserPagination(): Pager<Int, User> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { userDao.getUsersPagination() }
        )
    }

    override suspend fun getUserById(id: String): User? = userDao.getUserById(id)
}