package com.codeturtle.upfood.data

sealed class Resource <out R>{
    data class Success<out R>(val result: R) : Resource<R>()
    data class Failure(val error: String) : Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}