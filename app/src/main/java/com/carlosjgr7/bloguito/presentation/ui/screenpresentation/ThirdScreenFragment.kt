package com.carlosjgr7.bloguito.presentation.ui.screenpresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentThirdScreenBinding


class ThirdScreenFragment(private val viewPager: ViewPager2) : Fragment(R.layout.fragment_third_screen) {

    private lateinit var binding:FragmentThirdScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdScreenBinding.bind(view)


        binding.btnCardNext.setOnClickListener{
            viewPager.currentItem = 3
        }
    }
}