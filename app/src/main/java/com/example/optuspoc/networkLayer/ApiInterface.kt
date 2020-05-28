package com.example.optuspoc.networkLayer

import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.utility.Constants
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

/*
* @interface is used to declare network call methods.
* */

interface ApiInterface {
    /**
     * This API call will get the entire master data of USER
     */
    @GET(Constants.GET_USER_MASTER)
    fun getUserMasterList(): Call<ArrayList<ModelUserMaster>>

    /**
     * This method is getting for list's of photos from server
     * */
    @GET(Constants.GET_ALBUM)
    fun getPhotosList(@Query("albumId") albumId: String): Call<ArrayList<ModelPhoto>>
}