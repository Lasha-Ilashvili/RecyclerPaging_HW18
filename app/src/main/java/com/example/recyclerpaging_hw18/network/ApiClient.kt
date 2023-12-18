package com.example.recyclerpaging_hw18.network

import com.example.recyclerpaging_hw18.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiClient {

    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun userService(): UserService = retrofit.create(UserService::class.java)

//    fun getRegisterResult(): RegisterService = retrofit.create(RegisterService::class.java)
//
//    fun getLoginResult(): LoginService = retrofit.create(LoginService::class.java)
}