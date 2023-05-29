package com.carlosjgr7.bloguito.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.databinding.FragmentLoginBinding
import com.carlosjgr7.bloguito.databinding.FragmentSplashBinding
import java.util.concurrent.Executor


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        initVisualRules()
        initBiometrics()

        binding.btnAcces.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

        }
    }

    private fun initBiometrics() {
        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        requireContext(), "Authentication error: $errString", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                    ).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Authentication")
            .setSubtitle("Login quickly and securely with biometric recognition.")
            .setNegativeButtonText("CANCEL").build()

        binding.btnCardFingerprint.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
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
    }


}


