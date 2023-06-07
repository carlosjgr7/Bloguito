package com.carlosjgr7.bloguito.presentation.ui.screenpresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentLastScreenBinding
import com.carlosjgr7.bloguito.presentation.ui.viewmodel.PresentationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LastScreenFragment(private val presentationViewModel: PresentationViewModel) : Fragment(R.layout.fragment_last_screen) {

    private lateinit var binding:FragmentLastScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLastScreenBinding.bind(view)

        binding.btnToLogin.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                presentationViewModel.putViewedPresentation()
                withContext(Dispatchers.Main){
                    findNavController().navigate(R.id.action_presentationFragment_to_loginFragment)
                }
            }

        }


    }


}