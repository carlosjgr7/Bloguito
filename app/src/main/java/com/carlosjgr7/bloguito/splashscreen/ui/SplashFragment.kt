package com.carlosjgr7.bloguito.splashscreen.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.splashscreen.ui.viewmodel.SplashViewModel
import com.carlosjgr7.bloguito.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        requireView().postDelayed({
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {

                withContext(Dispatchers.Main) {
                    if (splashViewModel.getcheckingpresentation()) {
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_presentationFragment)
                    }
                }
            }
        }, 700)
    }

}