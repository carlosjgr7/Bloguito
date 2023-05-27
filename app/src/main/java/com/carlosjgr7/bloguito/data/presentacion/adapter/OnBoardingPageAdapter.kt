package com.carlosjgr7.bloguito.data.presentacion.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class OnBoardingPageAdapter(
    private val fragmentList: ArrayList<Fragment>,
    private val fm: FragmentManager,
    private val lifecycle: Lifecycle

    ) : FragmentStateAdapter(fm , lifecycle) {


    override fun getItemCount()= fragmentList.size

    override fun createFragment(position: Int)= fragmentList[position]

}