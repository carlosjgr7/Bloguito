package com.carlosjgr7.bloguito.presentation.domain

import com.carlosjgr7.bloguito.data.preferences.PreferencesRepository
import javax.inject.Inject

class PutPresentationViewedUsesCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke() = repository.putPresentationOnViwed()
}