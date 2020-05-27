package com.example.optuspoc.view

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optuspoc.R
import com.example.optuspoc.adapter.PhotoAlbumAdapter
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.utility.*
import com.example.optuspoc.viewModel.ViewModelAlbumInformation
import kotlinx.android.synthetic.main.fragment_album_information.view.*
import java.util.stream.Collectors

class AlbumInformationFragment : Fragment(), PhotoAlbumAdapter.OnItemClickListener {
    private lateinit var mAdapter: PhotoAlbumAdapter
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var mDataViewModel: ViewModelAlbumInformation

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_album_information, container, false)
        mDataViewModel = ViewModelProvider(this).get(ViewModelAlbumInformation::class.java)
        setupProgressDialog()
        activity?.title = getString(R.string.album_info)
        var bundle = arguments
        var mModelUserInformation: ModelUserMaster? = bundle?.getParcelable(Constants.userInfo)

        /**
         * Setting blank adapter for initialize
         */
        mAdapter = context?.let { PhotoAlbumAdapter(ArrayList(), it, this) }!!
        view.tv_id_album_information.text =
            getString(R.string.album_id).plus(mModelUserInformation?.id)
        view.album_information_view.layoutManager = LinearLayoutManager(context)
        view.album_information_view.adapter = mAdapter
        if (activity?.let { CheckNetworkConnection.isNetworkConnected(it) }!!) {
            showProgressDialog()
            mDataViewModel.getPhotosList()
        } else
            activity!!.toast(getString(R.string.internet_not_connected))

        mDataViewModel.mModelResponseHandle.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResponseStatus.FAIL -> {
                    hideProgressDialog()
                    activity!!.toast(getString(R.string.serviceFailureError))
                }


            }
        })
        mDataViewModel.mPhotosList.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            mAdapter.setList(filterList(it, mModelUserInformation))
        })
        return view
    }

    /**
     * get list of object matching with user ID
     * */
    private fun filterList(
        photoList: ArrayList<ModelPhoto>,
        mModelUserInformation: ModelUserMaster?
    ): ArrayList<ModelPhoto> {
        var mAdapterPhotoList = ArrayList<ModelPhoto>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return photoList.stream()
                .filter { p -> p.albumId == mModelUserInformation?.id }
                .map { pm -> pm }
                .collect(Collectors.toList()) as ArrayList<ModelPhoto>
        } else {
            for (list in photoList) {
                if (list.albumId == mModelUserInformation?.id) {
                    mAdapterPhotoList.add(list)
                }
            }
        }
        return mAdapterPhotoList
    }

    /**
     *Recycle item click listener
     * */
    override fun onItemClick(item: ModelPhoto?, position: Int, view: ImageView) {
        val bundle = Bundle()
        var transitionName = "transition"
        bundle.putParcelable(Constants.albumInfo, item)
        bundle.putString(Constants.transition, ViewCompat.getTransitionName(view))
        var mAlbumDetailFragment = AlbumDetailFragment()
        mAlbumDetailFragment.arguments = bundle

        showFragmentWithTransition(
            this,
            mAlbumDetailFragment.let { it },
            AlbumInformationFragment::class.java.name,
            view,
            transitionName
        )
    }

    private fun setupProgressDialog() {
        builder = AlertDialog.Builder(activity)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    private fun showProgressDialog() {
        dialog.let {
            if (!dialog.isShowing) {
                dialog.show()
            }
        }
    }

    private fun hideProgressDialog() {
        dialog.let {
            if (dialog.isShowing) {
                dialog.hide()
                dialog.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.let {
            if (dialog.isShowing) {
                dialog.hide()
                dialog.dismiss()
            }
        }
    }

    fun showFragmentWithTransition(
        current: Fragment,
        newFragment: Fragment,
        tag: String?,
        sharedView: ImageView?,
        sharedElementName: String
    ) {
        val fragmentManager = activity?.supportFragmentManager
        // check if the fragment is in back stack
        val fragmentPopped = fragmentManager?.popBackStackImmediate(tag, 0)
        if (fragmentPopped!!) {
            // fragment is pop from backStack
        } else {

            newFragment.sharedElementEnterTransition = DetailsTransition()
            newFragment.enterTransition = Fade()
            exitTransition = Fade()
            newFragment.sharedElementReturnTransition = DetailsTransition()

            val fragmentTransaction = fragmentManager.beginTransaction()
            sharedView?.let {
                fragmentTransaction.addSharedElement(
                    it,
                    ViewCompat.getTransitionName(it)!!
                )
            }
            fragmentTransaction.replace(R.id.landing_fragment_container, newFragment, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.commit()
        }
    }
}
