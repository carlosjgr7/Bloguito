package com.carlosjgr7.bloguito.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        initVisualRules()


    }

    private fun initVisualRules() {
        binding.etLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s?.toString()?.replace(" ", "")
                if (newText != s?.toString()) {
                    binding.etLogin.setText(newText)
                    binding.etLogin.setSelection(newText?.length ?: 0)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s?.toString()?.replace(" ", "")
                if (newText != s?.toString()) {
                    binding.etPass.setText(newText)
                    binding.etPass.setSelection(newText?.length ?: 0)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnAcces.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

        }
    }


}


