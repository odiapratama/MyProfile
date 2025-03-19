package com.problemsolver.feature.profile.domain

import android.content.Context
import com.problemsolver.core.data.local.UserDao
import com.problemsolver.core.data.model.User
import com.problemsolver.core.utils.isNetworkAvailable
import com.problemsolver.feature.profile.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val context: Context,
    private val userRepository: UserRepository,
    private val userDao: UserDao
) {
    operator fun invoke(): Flow<List<User>> {
        return flow {
            try {
                if (!isNetworkAvailable(context)) {
                    emit(userDao.getUsers())
                    return@flow
                }
                val users = userRepository.getUsers()
                userDao.insertUsers(users)
                emit(userDao.getUsers())
            } catch (e: Exception) {
                userDao.clearAll()
                emit(emptyList())
            }
        }
    }
}