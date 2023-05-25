package com.carlosjgr7.bloguito.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentMainBinding
import com.carlosjgr7.bloguito.databinding.FragmentSplashBinding
import com.google.firebase.storage.internal.Sleeper
import java.util.Timer
import java.util.TimerTask


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        requireView().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

        }, 700)
    }



}