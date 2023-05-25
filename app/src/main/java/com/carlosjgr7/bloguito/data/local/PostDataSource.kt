package com.carlosjgr7.bloguito.data.local

import com.carlosjgr7.bloguito.data.local.response.toPost
import com.carlosjgr7.bloguito.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostDataSource @Inject constructor(private val postDao:IPostDao) {

    suspend fun getUpcomingMovies(): List<Post> {
        return withContext(Dispatchers.IO) {
            val response = postDao.getAllPost()
            response.toPost()
        }
    }
}