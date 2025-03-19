package com.problemsolver.core.data.remote

import com.problemsolver.core.data.model.User
import retrofit2.http.GET

interface UserApi {
    @GET("getData/test")
    suspend fun getUser(): List<User>
}