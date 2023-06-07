package com.carlosjgr7.bloguito.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.presentation.adapter.OnBoardingPageAdapter
import com.carlosjgr7.bloguito.databinding.FragmentPresentationBinding
import com.carlosjgr7.bloguito.presentation.ui.screenpresentation.FirstScreenFragment
import com.carlosjgr7.bloguito.presentation.ui.screenpresentation.LastScreenFragment
import com.carlosjgr7.bloguito.presentation.ui.screenpresentation.SecondScreenFragment
import com.carlosjgr7.bloguito.presentation.ui.screenpresentation.ThirdScreenFragment
import com.carlosjgr7.bloguito.presentation.ui.viewmodel.PresentationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PresentationFragment : Fragment(R.layout.fragment_presentation) {
    private val presentationViewModel by viewModels<PresentationViewModel>()
    private lateinit var binding: FragmentPresentationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentationBinding.bind(view)


        val fragmentlist = arrayListOf(
            FirstScreenFragment(binding.viewPager),
            SecondScreenFragment(binding.viewPager),
            ThirdScreenFragment(binding.viewPager),
            LastScreenFragment(presentationViewModel)
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