package com.example.recyclerpaging_hw18.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val page: Int,
    @Json(name = "per_page") val perPage: Int,
    val total: Int,
    @Json(name = "total_pages") val totalPages: Int,
    val data: List<User>,
    val support: Support
)

@JsonClass(generateAdapter = true)
data class Support(val url: String, val text: String)
