package com.example.postapp.ui.postlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapp.data.repository.PostRepository
import com.example.postapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostListUiState>(PostListUiState.Loading)
    val uiState: StateFlow<PostListUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostListUiState.Loading
            when (val result = repository.getPosts()) {
                is NetworkResult.Error -> {
                    _uiState.value = PostListUiState.Error(result.message)
                }

                is NetworkResult.Success -> {
                    _uiState.value = PostListUiState.Success(result.data)
                }
            }
        }
    }

    fun deletePost(postId: Int) {
        val currenbState = _uiState.value
        if (currenbState is PostListUiState.Success) {
            val updatedList = currenbState.posts.filterNot { it.id == postId }
            _uiState.value = PostListUiState.Success(updatedList)
        }
    }

    fun updatePost(postId: Int, newTitle: String, newBody: String) {
        val currenbState = _uiState.value
        if (currenbState is PostListUiState.Success) {
            val updatedList = currenbState.posts.map { post ->
                if (post.id == postId) {
                    post.copy(title = newTitle, body = newBody)
                } else {
                    post
                }
            }
            _uiState.value = PostListUiState.Success(updatedList)
        }
    }
}