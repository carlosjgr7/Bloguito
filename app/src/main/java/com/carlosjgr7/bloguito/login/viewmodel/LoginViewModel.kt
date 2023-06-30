package com.carlosjgr7.bloguito.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.carlosjgr7.bloguito.core.Resources
import com.carlosjgr7.bloguito.data.model.Post
import com.carlosjgr7.bloguito.data.model.User
import com.carlosjgr7.bloguito.login.domain.GetUserRememberUsesCase
import com.carlosjgr7.bloguito.login.domain.SaveUserRememberUsesCase
import com.carlosjgr7.bloguito.login.domain.SignInFirebaseUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserRememberUsesCase: GetUserRememberUsesCase,
    private val saveUserRememberUsesCase: SaveUserRememberUsesCase,
    private val signInFirebaseUsesCase: SignInFirebaseUsesCase
) : ViewModel() {

    suspend fun saveLocalUser(user: User) = saveUserRememberUsesCase(user)
    suspend fun getLocalUser() = getUserRememberUsesCase()
    fun isValidEmail(email: String): Boolean {
        val emailRegex =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}$".toRegex(RegexOption.IGNORE_CASE)
        return emailRegex.matches(email)
    }
    fun singIn(user:User) = liveData(Dispatchers.IO) {
        emit(Resources.Loading())
        try{
            emit(Resources.Success(signInFirebaseUsesCase(user)))
        }catch (e: Exception){
            emit(Resources.Failure(e))
        }
    }


}