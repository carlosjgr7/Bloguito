package com.carlosjgr7.bloguito.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentLoginBinding
import com.carlosjgr7.bloguito.databinding.FragmentPresentationBinding


class PresentationFragment : Fragment(R.layout.fragment_presentation) {

    private lateinit var binding: FragmentPresentationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentationBinding.bind(view)

    }
}