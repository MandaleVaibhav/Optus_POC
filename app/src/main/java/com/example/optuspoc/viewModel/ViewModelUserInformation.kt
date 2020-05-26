package com.example.optuspoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.optuspoc.model.ModelResponseHandle
import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.repository.RepositoryViewModel
import com.example.optuspoc.utility.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelUserInformation(@NotNull application: Application) : AndroidViewModel(application),
    ResponseCallBack<Any> {
    var mModelResponseHandle = MutableLiveData<ModelResponseHandle>()
    private val repositoryViewModel: RepositoryViewModel by lazy { RepositoryViewModel() }
    val mUserInformationList: MutableLiveData<ArrayList<ModelUserMaster>> by lazy { MutableLiveData<ArrayList<ModelUserMaster>>() }

    fun getUserInformation() {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.LOADING, "")
        repositoryViewModel.retrieveUserInformationData(this)
    }

    /**
     * Success response callback from Repository
     * @param data for get updated list from API
     */
    override fun onSuccess(data: Any) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.SUCCESS, "")
        data.let { mUserInformationList.value = it as ArrayList<ModelUserMaster> }
    }

    /**
     * Failure response callback from Repository
     * @param error for get error message
     */
    override fun onFail(error: String?) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.FAIL, error)
    }
}