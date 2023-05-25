package com.carlosjgr7.bloguito.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentLoginBinding
import com.carlosjgr7.bloguito.databinding.FragmentSplashBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)


    }

}