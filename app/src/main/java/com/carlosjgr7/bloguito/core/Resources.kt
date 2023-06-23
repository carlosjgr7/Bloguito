package com.carlosjgr7.bloguito.core

import java.lang.Exception

sealed class Resources<out T>{
    class Loading<out T>:Resources<T>()
    data class Success<out T>(val data:T):Resources<T>()
    data class Failure(val exeption:Throwable):Resources<Nothing>()

}
