package com.carlosjgr7.bloguito.home.data

import com.carlosjgr7.bloguito.home.data.network.HomeNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeNetworkDataSource: HomeNetworkDataSource
) {
    fun getLatestPosts()= homeNetworkDataSource.getLatestPosts().flowOn(Dispatchers.IO)

}