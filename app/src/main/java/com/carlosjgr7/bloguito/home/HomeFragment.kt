package com.carlosjgr7.bloguito.home

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.core.Resources
import com.carlosjgr7.bloguito.databinding.FragmentMainBinding
import com.carlosjgr7.bloguito.home.adapter.HomeAdapter
import com.carlosjgr7.bloguito.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: HomeAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        lifecycleScope.launch {
            homeViewModel.getLatestsPost().collect {
                when (it) {
                    is Resources.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "ha ocurrido un error", Toast.LENGTH_SHORT).show()
                    }

                    is Resources.Loading -> binding.progressBar.visibility = View.VISIBLE

                    is Resources.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter = HomeAdapter(it.data)
                        binding.rvHome.adapter = adapter
                    }
                }
            }
        }
    }
}