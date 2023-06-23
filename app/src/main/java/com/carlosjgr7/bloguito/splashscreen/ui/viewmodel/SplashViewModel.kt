package com.carlosjgr7.bloguito.splashscreen.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.carlosjgr7.bloguito.splashscreen.domain.GetPresentationViewedUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getPresentationViewedUsesCase: GetPresentationViewedUsesCase
) : ViewModel() {

    suspend fun getcheckingpresentation():Boolean = getPresentationViewedUsesCase().map {it}.first()


}