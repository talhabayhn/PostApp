package com.example.postapp.data.remote

import com.example.postapp.data.model.PostDto
import retrofit2.http.GET

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
