package com.example.optuspoc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.optuspoc.R
import com.example.optuspoc.databinding.FragmentAlbumDetailBinding
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.utility.Constants
import com.example.optuspoc.viewModel.ViewModelAlbumInformation

class AlbumDetailFragment : Fragment() {
    private lateinit var mDataViewModel: ViewModelAlbumInformation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        mDataViewModel = ViewModelProvider(this).get(ViewModelAlbumInformation::class.java)

        val binding: FragmentAlbumDetailBinding = FragmentAlbumDetailBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding.root
        activity?.title = getString(R.string.album_detail)

        val user: ModelPhoto? = bundle?.getParcelable(Constants.albumInfo)
        user?.let {
            binding.userDetail = user
        }
        return view
    }
}
