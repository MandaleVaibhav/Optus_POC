package com.example.optuspoc.view

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optuspoc.LandingScreenActivity
import com.example.optuspoc.R
import com.example.optuspoc.adapter.UserInformationAdapter
import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.utility.CheckNetworkConnection.Companion.isNetworkConnected
import com.example.optuspoc.utility.Constants
import com.example.optuspoc.utility.ResponseStatus
import com.example.optuspoc.utility.toast
import com.example.optuspoc.viewModel.ViewModelUserInformation
import kotlinx.android.synthetic.main.fragment_user_information.view.*

class UserInformationFragment : Fragment(), UserInformationAdapter.OnItemClickListener {

    private lateinit var mAdapter: UserInformationAdapter
    private lateinit var mDataViewModel: ViewModelUserInformation
    private lateinit var builder: AlertDialog.Builder
    private var dialog: AlertDialog? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_user_information, container, false)
        setupProgressDialog()
        activity?.title = getString(R.string.user_info)
        mDataViewModel = ViewModelProvider(this).get(ViewModelUserInformation::class.java)
        if (activity?.let { isNetworkConnected(it) }!!) {
            showProgressDialog()
            mDataViewModel.getUserInformation()
        } else {
            activity!!.toast(getString(R.string.internet_not_connected))
        }


        /**
         * Setting blank adapter for initialize
         */
        mAdapter = context?.let { UserInformationAdapter(ArrayList(), it, this) }!!
        view.user_list.layoutManager = LinearLayoutManager(context)
        view.user_list.adapter = mAdapter

        mDataViewModel.mModelResponseHandle.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResponseStatus.FAIL -> {
                    activity!!.toast(getString(R.string.serviceFailureError))
                }
            }
        })
        mDataViewModel.mUserInformationList.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)
            hideProgressDialog()
        })

        return view
    }

    private fun setupProgressDialog() {
        builder = AlertDialog.Builder(activity)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    private fun showProgressDialog() {
        dialog.let {
            if (!dialog?.isShowing!!) {
                dialog?.show()
            }
        }

    }

    private fun hideProgressDialog() {
        dialog.let {
            if (dialog?.isShowing!!) {
                dialog?.hide()
                dialog?.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.let {
            if (dialog?.isShowing!!) {
                dialog?.hide()
                dialog?.dismiss()
            }
        }
    }

    override fun onItemClick(item: ModelUserMaster?) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.userInfo, item)
        var mAlbumInformationFragment = AlbumInformationFragment()
        mAlbumInformationFragment.arguments = bundle
        (context as LandingScreenActivity).showFragment(
            mAlbumInformationFragment.let { it },
            AlbumInformationFragment::class.java.name
        )
    }

}