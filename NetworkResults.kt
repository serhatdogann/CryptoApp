package com.example.kripto.utils

sealed class NetworkResults<T>(
    val data:T?=null,
    val message:String?=null,
    val networkError:Boolean?=null
) {

    class Success<T>(data: T?):NetworkResults<T>(data)
    class Error<T>(networkError: Boolean?,message: String?):NetworkResults<T>(data = null,message=message,networkError=networkError)


}
