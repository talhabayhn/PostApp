package com.example.postapp.data.model

import com.example.postapp.util.Constants

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val imageUrl: String = "${Constants.IMAGE_BASE_URL}?random=$id&grayscale"
)
