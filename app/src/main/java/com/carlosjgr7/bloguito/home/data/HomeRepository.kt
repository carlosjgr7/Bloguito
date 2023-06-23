package com.carlosjgr7.bloguito.home.data

import com.carlosjgr7.bloguito.data.model.Post
import com.carlosjgr7.bloguito.home.data.network.HomeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDataSource: HomeDataSource
) {

    suspend fun getLatestPosts()= homeDataSource.getLatestPosts().flowOn(Dispatchers.IO)

}