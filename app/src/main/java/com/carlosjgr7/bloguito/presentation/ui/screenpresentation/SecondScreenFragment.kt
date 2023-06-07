package com.carlosjgr7.bloguito.presentation.ui.screenpresentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentSecondScreenBinding

class SecondScreenFragment(private val viewPager: ViewPager2) : Fragment(R.layout.fragment_second_screen) {

    private lateinit var binding:FragmentSecondScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondScreenBinding.bind(view)


        binding.btnCardNext.setOnClickListener{
            viewPager.currentItem = 2
        }
    }

}