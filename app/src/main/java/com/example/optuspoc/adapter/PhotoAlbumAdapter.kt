package com.example.optuspoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optuspoc.BR
import com.example.optuspoc.R
import com.example.optuspoc.databinding.PhotoItemBinding
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.utility.Constants

class PhotoAlbumAdapter(
    var photoList: ArrayList<ModelPhoto>,
    var context: Context,
    var listener: OnItemClickListener
) : RecyclerView.Adapter<PhotoAlbumAdapter.ViewHolder>() {

    /**
     * this method is returning the view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: PhotoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.photo_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    /**
     * This method for setting list to current list from another class
     * @param userList for to get updated list
     */
    fun setList(userList: ArrayList<ModelPhoto>) {
        this.photoList = userList
        notifyDataSetChanged()
    }

    /**
     * This method for set data to item.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position], listener, position)
    }

    /**
     * @return the size of the list.
     */
    override fun getItemCount(): Int {
        return photoList.size
    }

    /**
     * Recycler View click listener interface
     * */
    interface OnItemClickListener {
        fun onItemClick(item: ModelPhoto?, position: Int, view: ImageView)
    }

    /**
     * This class for creating items
     */
    class ViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var imageView = itemView.findViewById(R.id.listImage) as ImageView

        fun bind(data: ModelPhoto, listener: OnItemClickListener, position: Int) {
            ViewCompat.setTransitionName(imageView, Constants.transition)
            itemView.setOnClickListener {
                listener.onItemClick(data, position, imageView)
            }
            binding.setVariable(
                BR.data,
                data
            )
            binding.data = data
            binding.executePendingBindings()
        }
    }
}