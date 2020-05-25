package com.example.optuspoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optuspoc.BR
import com.example.optuspoc.R
import com.example.optuspoc.databinding.UserInfoItemBinding
import com.example.optuspoc.model.ModelUserMaster

/**
 * Adapter class for will populate the data in list
 * */

class UserInformationAdapter(
    var userList: ArrayList<ModelUserMaster>,
    var context: Context,
    var listener: OnItemClickListener
) : RecyclerView.Adapter<UserInformationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserInfoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.user_info_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position], listener)
    }

    /**
     * Item click listener
     * */
    interface OnItemClickListener {
        fun onItemClick(item: ModelUserMaster?)
    }

    /**
     * @return the size of the list.
     */
    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(private val binding: UserInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelUserMaster, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(data) }
            binding.setVariable(BR.data, data)
            binding.data = data
            binding.executePendingBindings()
        }
    }

    /**
     * This method for setting list to current list from another class
     * @param userList for to get updated list
     */
    fun setList(userList: ArrayList<ModelUserMaster>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}