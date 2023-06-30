package com.carlosjgr7.bloguito.core.preferences

import com.carlosjgr7.bloguito.data.model.User
import kotlinx.coroutines.flow.Flow

interface Preferences {
    suspend fun putPresentationOnViwed()
    suspend fun getPresentationViwed(): Flow<Boolean>
    suspend fun putUser(user: User)
    suspend fun getUser(): Flow<User>


}