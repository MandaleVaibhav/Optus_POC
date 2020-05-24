package com.example.optuspoc.networkLayer

import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.utility.Constants
import retrofit2.http.GET
import retrofit2.Call

/*
* @interface is used to declare network call methods.
* */

interface ApiInterface {
    /**
     * This API call will get the entire master data of USER
     */
    @GET(Constants.GET_USER_MASTER)
    fun getUserMasterList(): Call<ArrayList<ModelUserMaster>>
}