package com.carlosjgr7.bloguito.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.core.Resources
import com.carlosjgr7.bloguito.databinding.FragmentHomeBinding
import com.carlosjgr7.bloguito.home.adapter.HomeAdapter
import com.carlosjgr7.bloguito.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        homeViewModel.getLatestsPost()

        lifecycleScope.launch {
            homeViewModel.postState.collect{
                when(it){
                    is Resources.Failure -> {
                        binding.shimmerViewContainer.visibility =View.GONE
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                    is Resources.Loading -> {
                        binding.shimmerViewContainer.visibility = View.VISIBLE
                    }
                    is Resources.Success -> {
                        binding.shimmerViewContainer.visibility =View.GONE
                        adapter = HomeAdapter(it.data)
                        binding.rvHome.adapter = adapter
                    }
                }
            }
        }
    }
}