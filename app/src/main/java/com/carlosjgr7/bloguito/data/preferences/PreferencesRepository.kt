package com.carlosjgr7.bloguito.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.carlosjgr7.bloguito.core.preferences.Preferences
import com.carlosjgr7.bloguito.data.model.User
import com.carlosjgr7.bloguito.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val PRESENTATION_VIWED = "PRESENTATION_VIWED"
const val USER = "USER"
const val PASS = "PASS"

class PreferencesRepository @Inject constructor(
    private val context: Context
) : Preferences {

    override suspend fun putPresentationOnViwed() {
        context.dataStore.edit {
            it[booleanPreferencesKey(PRESENTATION_VIWED)] = true
        }
    }

    override suspend fun getPresentationViwed(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[booleanPreferencesKey(PRESENTATION_VIWED)] ?: false
        }
    }

    override suspend fun putUser(user: User) {
        context.dataStore.edit {
            it[stringPreferencesKey(USER)] = user.user
        }
        context.dataStore.edit {
            it[stringPreferencesKey(PASS)] = user.pass
        }
    }

    override suspend fun getUser() = context.dataStore.data.map {
        User(
            user = it[stringPreferencesKey(USER)].orEmpty(),
            pass = it[stringPreferencesKey(PASS)].orEmpty()
        )
    }
}


