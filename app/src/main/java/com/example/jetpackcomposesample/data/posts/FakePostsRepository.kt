package com.example.jetpackcomposesample.data.posts

import com.example.jetpackcomposesample.data.ApiState
import com.example.jetpackcomposesample.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

/**
 * Implementation of PostsRepository that returns a hardcoded list of
 * posts with resources after some delay in a background thread.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FakePostsRepository : PostsRepository {

    override suspend fun getPost(postId: String?): ApiState<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }
            if (post == null) {
                ApiState.Error(IllegalArgumentException("Post not found"))
            } else {
                ApiState.Success(post)
            }
        }
    }

//    override suspend fun getPostsFeed(): Result<PostsFeed> {
//        return withContext(Dispatchers.IO) {
//            delay(800) // pretend we're on a slow network
//            if (shouldRandomlyFail()) {
//                Result.Error(IllegalStateException())
//            } else {
//                Result.Success(posts)
//            }
//        }
//    }
//
//    override fun observeFavorites(): Flow<Set<String>> = favorites
//
//    override suspend fun toggleFavorite(postId: String) {
//        mutex.withLock {
//            val set = favorites.value.toMutableSet()
//            set.addOrRemove(postId)
//            favorites.value = set.toSet()
//        }
//    }

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}
