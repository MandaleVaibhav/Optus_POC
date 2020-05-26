package com.example.optuspoc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.optuspoc.R
import coil.api.load
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.utility.Constants
import com.example.optuspoc.viewModel.ViewModelAlbumInformation
import kotlinx.android.synthetic.main.fragment_album_detail.view.*

class AlbumDetailFragment : Fragment() {
    private lateinit var mDataViewModel: ViewModelAlbumInformation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_album_detail, container, false)
        val view = inflater.inflate(R.layout.fragment_album_detail, container, false)
        activity?.title = getString(R.string.album_detail)
        val bundle = arguments
        val mModelPhotosResponse: ModelPhoto? = bundle?.getParcelable(Constants.albumInfo)
        //var transitionName: String? = bundle?.getString(Constant.transition)
        //view.albumImage.transitionName = transitionName

        /*Glide.with(this)
            .load(mModelPhotosResponse?.url)
            .placeholder(R.drawable.no_image)
            .into(view.albumImage)*/
        view.albumImage.load(mModelPhotosResponse?.url)
        {
            crossfade(true)
            placeholder(R.drawable.no_image)
            error(R.drawable.no_image)
        }
        view.imageInfo.text = mModelPhotosResponse?.title
        view.albumId.text = getString(R.string.album_id).plus(mModelPhotosResponse?.albumId)
        view.photoId.text = getString(R.string.photo_id).plus(mModelPhotosResponse?.id)
        mDataViewModel = ViewModelProvider(this).get(ViewModelAlbumInformation::class.java)
        return view
    }
}
