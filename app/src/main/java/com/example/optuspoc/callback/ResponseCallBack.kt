package com.example.optuspoc.callback

/*
* @interface will help to handle retrofit callback*/
interface ResponseCallBack<T> {

    fun onFail(error: String?)
    fun onSuccess(data: T)
}