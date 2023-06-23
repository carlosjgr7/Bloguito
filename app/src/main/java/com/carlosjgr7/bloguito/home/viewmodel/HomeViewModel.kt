package com.carlosjgr7.bloguito.home.viewmodel

import androidx.lifecycle.ViewModel
import com.carlosjgr7.bloguito.core.Resources
import com.carlosjgr7.bloguito.home.domain.GetLatestPostsUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestPostsUsesCase: GetLatestPostsUsesCase
) : ViewModel() {


    fun getLatestsPost() = flow {
        emit(Resources.Loading())
        getLatestPostsUsesCase().collect {
            emit(Resources.Success(it))
        }
    }.catch {e-> Resources.Failure (e) }


}