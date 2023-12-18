package com.example.recyclerpaging_hw18.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recyclerpaging_hw18.PagingSource
import com.example.recyclerpaging_hw18.model.User
import com.example.recyclerpaging_hw18.network.ApiClient
import kotlinx.coroutines.flow.Flow

class UserListViewModel : ViewModel() {
//
//    private val _userResult = MutableStateFlow<ResultResponse<UserResponse>?>(null)
//    val userResult get() = _userResult.asStateFlow()
//
//    init {
//        setInitialList()
//    }
//
//    private fun setInitialList() {
//        viewModelScope.launch {
//            try {
//                val response = ApiClient.userService().getUsers(page = 1)
//
//                if (response.isSuccessful) {
//                    _userResult.value = ResultResponse.Success(response.body()!!)
//                } else {
//                    val r = response.errorBody().toString()
//                    val moshi = Moshi.Builder().build()
//                    val adapter = moshi.adapter(String::class.java)
//
//                    _userResult.value =
//                        ResultResponse.Error("Failed to fetch chats ${adapter.fromJson(r)!!}")
//                }
//
//            } catch (e: IOException) {
//                _userResult.value = ResultResponse.Error("Network error: ${e.message}")
//            }
//        }
//    }

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 6, enablePlaceholders = false),
            pagingSourceFactory = { PagingSource(ApiClient.userService()) }
        ).flow
    }
}