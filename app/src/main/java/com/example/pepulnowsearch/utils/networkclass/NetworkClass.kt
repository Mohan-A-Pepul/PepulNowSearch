package com.example.pepulnowsearch.utils.networkclass

sealed class NetworkClass<out T>(){
    object Loading: NetworkClass<Nothing>()
    object Empty: NetworkClass<Nothing>()
    data class Success<T>(val item: T): NetworkClass<T>()
    data class Error<T>(val error:String): NetworkClass<T>()
}
