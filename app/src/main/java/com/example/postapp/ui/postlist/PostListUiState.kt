package com.example.postapp.ui.postlist

import com.example.postapp.data.model.Post

sealed class PostListUiState {

    data object Loading : PostListUiState()
    data class Success(val posts: List<Post>) : PostListUiState()
    data class Error(val message: String) : PostListUiState()

}