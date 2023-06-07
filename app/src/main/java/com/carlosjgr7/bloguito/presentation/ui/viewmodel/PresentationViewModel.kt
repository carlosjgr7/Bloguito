package com.carlosjgr7.bloguito.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.carlosjgr7.bloguito.presentation.domain.PutPresentationViewedUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PresentationViewModel @Inject constructor(
    private val putPresentationViewedUsesCase: PutPresentationViewedUsesCase
) : ViewModel() {

    suspend fun putViewedPresentation() = putPresentationViewedUsesCase()

}