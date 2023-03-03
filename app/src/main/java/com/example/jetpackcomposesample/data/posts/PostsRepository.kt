package com.example.jetpackcomposesample.data.posts

import com.example.jetpackcomposesample.data.ApiState
import com.example.jetpackcomposesample.data.model.Post

interface PostsRepository {

    //APIから情報を取ってくる
    suspend fun getPost(postId: String?): ApiState<Post>
}