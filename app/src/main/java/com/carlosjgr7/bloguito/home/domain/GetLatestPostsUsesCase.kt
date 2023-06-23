package com.carlosjgr7.bloguito.home.domain

import com.carlosjgr7.bloguito.home.data.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLatestPostsUsesCase @Inject constructor(
    private val repository: HomeRepository
)
{
    suspend operator fun invoke() = repository.getLatestPosts().flowOn(Dispatchers.IO)
}