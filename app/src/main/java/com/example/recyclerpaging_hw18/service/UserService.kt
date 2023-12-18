package com.example.recyclerpaging_hw18.service

import com.example.recyclerpaging_hw18.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UserResponse>
}