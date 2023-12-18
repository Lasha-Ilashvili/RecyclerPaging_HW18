package com.example.recyclerpaging_hw18.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerpaging_hw18.model.UserResponse
import com.example.recyclerpaging_hw18.network.ApiClient
import com.example.recyclerpaging_hw18.resources.ResultResponse
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class UserListViewModel : ViewModel() {

    private val _userResult = MutableStateFlow<ResultResponse<UserResponse>?>(null)
    val userResult get() = _userResult.asStateFlow()

    init {
        setInitialList()
    }

    private fun setInitialList() {
        viewModelScope.launch {
            try {
                val response = ApiClient.userService().getUsers(page = 1)

                if (response.isSuccessful) {
                    _userResult.value = ResultResponse.Success(response.body()!!)
                } else {
                    val r = response.errorBody().toString()
                    val moshi = Moshi.Builder().build()
                    val adapter = moshi.adapter(String::class.java)

                    _userResult.value =
                        ResultResponse.Error("Failed to fetch chats ${adapter.fromJson(r)!!}")
                }

            } catch (e: IOException) {
                _userResult.value = ResultResponse.Error("Network error: ${e.message}")
            }
        }
    }
}