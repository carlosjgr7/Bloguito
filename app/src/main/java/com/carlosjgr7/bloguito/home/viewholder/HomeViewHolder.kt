package com.carlosjgr7.bloguito.home.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlosjgr7.bloguito.data.model.Post
import com.carlosjgr7.bloguito.databinding.ItemPostBinding

class HomeViewHolder(
    private val binding: ItemPostBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Post) {
        Glide.with(context).load(item.post_image).centerCrop().into(binding.postImage)
        Glide.with(context).load(item.post_profile_picture).centerCrop().into(binding.imgProfile)
        binding.tvName.text = item.post_profile_name
        binding.tvDate.text = "hace 2 horas"
        binding.tvDescription.text = item.post_description
    }
}