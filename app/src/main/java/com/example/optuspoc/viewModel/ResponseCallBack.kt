package com.example.optuspoc.viewModel

/*
* @interface will help to handle retrofit callback*/
interface ResponseCallBack<T> {

    fun onFail(error: String?)
    fun onSuccess(data: T)
}