package com.carlosjgr7.bloguito.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.data.presentacion.adapter.OnBoardingPageAdapter
import com.carlosjgr7.bloguito.databinding.FragmentPresentationBinding
import com.carlosjgr7.bloguito.ui.presentation.screenpresentation.FirstScreenFragment
import com.carlosjgr7.bloguito.ui.presentation.screenpresentation.LastScreenFragment
import com.carlosjgr7.bloguito.ui.presentation.screenpresentation.SecondScreenFragment
import com.carlosjgr7.bloguito.ui.presentation.screenpresentation.ThirdScreenFragment


class PresentationFragment : Fragment(R.layout.fragment_presentation) {

    private lateinit var binding: FragmentPresentationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentationBinding.bind(view)

        val fragmentlist = arrayListOf(
            FirstScreenFragment(binding.viewPager),
            SecondScreenFragment(binding.viewPager),
            ThirdScreenFragment(binding.viewPager),
            LastScreenFragment()
        )

        val adapter = OnBoardingPageAdapter(
            fragmentlist,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)


    }
}