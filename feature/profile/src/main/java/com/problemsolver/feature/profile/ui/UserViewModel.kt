package com.problemsolver.feature.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.problemsolver.core.data.model.User
import com.problemsolver.core.data.model.userEmpty
import com.problemsolver.core.utils.safeApiCall
import com.problemsolver.feature.profile.domain.UserUseCase
import com.problemsolver.feature.profile.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userUseCase: UserUseCase
) : ViewModel() {

    init {
        getUsers()
    }

    private val mutableUsers = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val users: StateFlow<UserUiState>
        get() = mutableUsers

    private fun getUsers() {
        viewModelScope.launch {
            safeApiCall(
                onError = {
                    mutableUsers.emit(UserUiState.Error(it.message ?: "Something went wrong"))
                }, {
                    userUseCase().collectLatest {
                        if (it.isEmpty()) mutableUsers.emit(UserUiState.Loading)
                        else mutableUsers.emit(UserUiState.Success(it))
                    }
                }
            )
        }
    }

    private val mutableUser = MutableStateFlow(userEmpty())
    val user: StateFlow<User> = mutableUser

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            val user = userRepository.getUserById(userId)
            if (user != null) mutableUser.value = user
        }
    }
}

sealed interface UserUiState {
    data object Loading : UserUiState
    data object Empty : UserUiState
    data class Success(
        val data: List<User> = emptyList(),
        val isPageLoading: Boolean = false
    ) : UserUiState

    data class Error(
        val message: String
    ) : UserUiState
}