package com.carlosjgr7.bloguito.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosjgr7.bloguito.data.model.Post
import com.carlosjgr7.bloguito.databinding.ItemPostBinding
import com.carlosjgr7.bloguito.home.viewholder.HomeViewHolder

class HomeAdapter(
    private val listpost: List<Post>,
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, parent.context)
    }

    override fun getItemCount() = listpost.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(listpost[position])
    }

}