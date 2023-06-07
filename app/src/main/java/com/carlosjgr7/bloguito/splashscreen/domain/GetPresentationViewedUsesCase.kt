package com.carlosjgr7.bloguito.splashscreen.domain

import com.carlosjgr7.bloguito.data.preferences.PreferencesRepository
import javax.inject.Inject

class GetPresentationViewedUsesCase @Inject constructor(private val repository: PreferencesRepository) {
    suspend operator fun invoke() = repository.getPresentationViwed()

}