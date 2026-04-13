package com.example.postapp.data.repository

import com.example.postapp.data.model.Post
import com.example.postapp.data.model.toPostList
import com.example.postapp.data.remote.PostApiService
import com.example.postapp.util.NetworkResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val apiService: PostApiService
) {

    suspend fun getPosts(): NetworkResult<List<Post>> {
        return safeApiCall {
            apiService.getPosts().toPostList()
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
        return try {
            NetworkResult.Success(apiCall())
        } catch (e: HttpException) {
            NetworkResult.Error(
                message = "Server error (${e.code()}). Please try again.",
                cause = e
            )
        } catch (e: IOException) {
            NetworkResult.Error(
                message = "Network error. Please check your connection.",
                cause = e
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                message = e.message ?: "An unexpected error occurred.",
                cause = e
            )
        }
    }
}
