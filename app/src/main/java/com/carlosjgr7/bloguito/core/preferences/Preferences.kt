package com.carlosjgr7.bloguito.core.preferences

import kotlinx.coroutines.flow.Flow

interface Preferences {
    suspend fun putPresentationOnViwed()
    suspend fun getPresentationViwed(): Flow<Boolean>
}