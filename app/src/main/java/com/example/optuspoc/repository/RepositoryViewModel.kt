package com.example.optuspoc.repository

import com.example.optuspoc.callback.ResponseCallBack
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.networkLayer.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel {

    /**
     * This method for getting list of objects from the server
     * @param objCallback for get response to View Model
     */
    fun retrieveUserInformationData(objCallback: ResponseCallBack<Any>) {
        val data: Call<ArrayList<ModelUserMaster>>? = ApiClient.build()?.getUserMasterList()
        val enqueue = data?.enqueue(object : Callback<ArrayList<ModelUserMaster>> {
            override fun onResponse(
                call: Call<ArrayList<ModelUserMaster>>,
                response: Response<ArrayList<ModelUserMaster>>
            ) {
                if (response.isSuccessful) {
                    /**
                     * Send success response to viewModel using this callback
                     */
                    response.body()?.let { objCallback.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<ArrayList<ModelUserMaster>>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onFail(t.message)
            }
        })
    }

    fun retrievePhotos(objCallback: ResponseCallBack<Any>) {
        val data: Call<ArrayList<ModelPhoto>>? = ApiClient.build()?.getPhotosList()
        val enqueue = data?.enqueue(object : Callback<ArrayList<ModelPhoto>> {
            override fun onResponse(
                call: Call<ArrayList<ModelPhoto>>,
                response: Response<ArrayList<ModelPhoto>>
            ) {
                if (response.isSuccessful) {
                    /**
                     * Send success response to viewModel using this callback
                     */
                    response.body()?.let { objCallback.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<ArrayList<ModelPhoto>>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onFail(t.message)
            }
        })
    }
}