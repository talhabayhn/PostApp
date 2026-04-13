package com.example.postapp.data.model

fun PostDto.toPost(): Post = Post(
    id = id ?: 0,
    userId = userId ?: 0,
    title = title.orEmpty(),
    body = body.orEmpty()
)
fun List<PostDto>.toPostList(): List<Post> = map { it.toPost() }