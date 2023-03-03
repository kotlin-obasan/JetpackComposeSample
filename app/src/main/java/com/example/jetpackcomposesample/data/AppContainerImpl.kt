package com.example.jetpackcomposesample.data

import android.content.Context
import com.example.jetpackcomposesample.data.posts.FakePostsRepository
import com.example.jetpackcomposesample.data.posts.PostsRepository

interface AppContainer {
    val postsRepository: PostsRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }

}
