package com.example.optuspoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.model.ModelResponseHandle
import com.example.optuspoc.repository.RepositoryViewModel
import com.example.optuspoc.utility.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelAlbumInformation(@NotNull application: Application) : AndroidViewModel(application),
    ResponseCallBack<Any> {

    private val repositoryViewModel: RepositoryViewModel by lazy { RepositoryViewModel() }
    val mPhotosList: MutableLiveData<ArrayList<ModelPhoto>> by lazy { MutableLiveData<ArrayList<ModelPhoto>>() }
    var mModelResponseHandle = MutableLiveData<ModelResponseHandle>()
    fun getPhotosList(id:String) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.LOADING, "")
        repositoryViewModel.retrievePhotos(this,id)
    }

    /**
     * Failure response callback from Repository
     * @param error for get error message
     */
    override fun onFail(error: String?) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.FAIL, error)
    }

    /**
     * Success response callback from Repository
     * @param data for get updated list from API
     */
    override fun onSuccess(data: Any) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.SUCCESS, "")
        data.let {
            mPhotosList.value = data as ArrayList<ModelPhoto>
        }
    }
}