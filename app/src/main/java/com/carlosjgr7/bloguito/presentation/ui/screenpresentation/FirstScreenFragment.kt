package com.carlosjgr7.bloguito.presentation.ui.screenpresentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentFirstScreenBinding

class FirstScreenFragment(private val viewPager: ViewPager2) : Fragment(R.layout.fragment_first_screen) {

    private lateinit var binding: FragmentFirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstScreenBinding.bind(view)


        binding.btnCardNext.setOnClickListener{
            viewPager.currentItem = 1
        }

    }


}