package com.carlosjgr7.bloguito.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjgr7.bloguito.core.Resources
import com.carlosjgr7.bloguito.data.model.Post
import com.carlosjgr7.bloguito.home.domain.GetLatestPostsUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestPostsUsesCase: GetLatestPostsUsesCase
) : ViewModel() {

    private val _postState = MutableStateFlow<Resources<List<Post>>>(Resources.Loading())
    val postState:StateFlow<Resources<List<Post>>> = _postState

    fun getLatestsPost(){
        viewModelScope.launch {
            getLatestPostsUsesCase()
                .catch {_postState.value = Resources.Failure(it)  }
                .flowOn(Dispatchers.IO)
                .collect{
                _postState.value = Resources.Success(it)
            }
        }
    }

}