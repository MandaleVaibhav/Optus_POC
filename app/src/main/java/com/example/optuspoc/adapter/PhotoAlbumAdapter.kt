package com.example.optuspoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bumptech.glide.Glide
import com.example.optuspoc.BR
import com.example.optuspoc.R
import com.example.optuspoc.databinding.PhotoItemBinding
import com.example.optuspoc.model.ModelPhoto

class PhotoAlbumAdapter(
    var photoList: ArrayList<ModelPhoto>,
    var context: Context,
    var listener: OnItemClickListener
) : RecyclerView.Adapter<PhotoAlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: PhotoItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.photo_item,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    /**
     * This method for setting list to current list from another class
     * @param PhotoList for to get updated list
     */
    fun setList(userList: ArrayList<ModelPhoto>) {
        this.photoList = userList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position], listener, position)
    }

    /**
     * This class for creating items
     */
    class ViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var imageView = itemView.findViewById(R.id.listImage) as ImageView

        fun bind(data: ModelPhoto, listener: OnItemClickListener, position: Int) {
            itemView.setOnClickListener {
                listener.onItemClick(data, position, imageView)
            }
/*            Glide.with(itemView)
                .load(data.thumbnailUrl)
                .placeholder(R.drawable.no_image)
                .into(binding.listImage)*/

            binding.listImage.load(data.thumbnailUrl)
            {
                crossfade(true)
                placeholder(R.drawable.no_image)
                error(R.drawable.no_image)
            }
            binding.setVariable(
                BR.data,
                data
            )
            binding.data = data
            binding.executePendingBindings()
        }
    }

    /**
     * Recycler View click listener interface
     * */
    interface OnItemClickListener {
        fun onItemClick(item: ModelPhoto?, position: Int, view: ImageView)
    }

}