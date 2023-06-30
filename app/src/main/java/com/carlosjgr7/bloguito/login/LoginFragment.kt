package com.carlosjgr7.bloguito.login

import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.core.Resources
import com.carlosjgr7.bloguito.data.model.ErrorFirebase
import com.carlosjgr7.bloguito.data.model.User
import com.carlosjgr7.bloguito.databinding.FragmentLoginBinding
import com.carlosjgr7.bloguito.login.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val loginViewModel: LoginViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        initVisualRules()
        initBiometrics()
        getLocalData()
        isUserLogin()

        binding.btnAccess.setOnClickListener {
            validateCredentials(
                binding.etLogin.text.toString().trim(),
                binding.etPass.text.toString().trim()
            )
        }
        binding.btnCardFingerprint.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
        binding.tvSingup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }


    private fun getLocalData() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            loginViewModel.getLocalUser().catch { Log.i("TAGITO", it.message.toString()) }.collect {
                binding.etLogin.setText(it.user)
                binding.etPass.setText(it.pass)

            }
        }
    }
    private fun disableElements() {
        binding.btnCardFingerprint.isEnabled = false
        binding.etLogin.isEnabled = false
        binding.etPass.isEnabled = false
        binding.btnFacebook.isEnabled = false
        binding.btnGoogle.isEnabled = false
        binding.cbRemember.isEnabled = false
        binding.tvSingup.isEnabled = false
    }
    private fun enableElements() {
        binding.btnCardFingerprint.isEnabled = true
        binding.etLogin.isEnabled = true
        binding.etPass.isEnabled = true
        binding.btnFacebook.isEnabled = true
        binding.btnGoogle.isEnabled = true
        binding.cbRemember.isEnabled = true
        binding.tvSingup.isEnabled = true
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
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                        getLocalData()
                        withContext(Dispatchers.Main) {
                            if (binding.etLogin.text.toString().isEmpty()) {
                                Toast.makeText(
                                    requireContext(),
                                    "No tiene datos almacenados",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@withContext
                            }
                            binding.btnAccess.performClick()
                        }
                    }
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
    }
    private fun initVisualRules() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val handleDrawable: Drawable = binding.etLogin.textSelectHandle!!
            handleDrawable.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                ), PorterDuff.Mode.SRC_IN
            )
        }

        binding.etLogin.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.layoutLogin.isErrorEnabled = false
            }

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
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.layoutPass.isErrorEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.layoutPass.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE)
                val newText = s?.toString()?.replace(" ", "")
                if (newText != s?.toString()) {
                    binding.etPass.setText(newText)
                    binding.etPass.setSelection(newText?.length ?: 0)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun isUserLogin() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
    private fun validateCredentials(email: String, pass: String) {
        if (email.isEmpty()) {
            binding.layoutLogin.error = "empty email"
            return
        }

        if (!loginViewModel.isValidEmail(email)) {
            binding.layoutLogin.error = "invalid email"
            return
        }

        if (pass.isEmpty()) {
            binding.layoutPass.error = "password is empty"
            return
        }

        if (pass.length<6) {
            binding.layoutPass.error = "size must be a minimum of 6"
            return
        }

        singin(email, pass)
    }
    private fun singin(user: String, pass: String) {
        loginViewModel.singIn(User(user, pass)).observe(viewLifecycleOwner) {
            when (it) {
                is Resources.Failure -> {
                    binding.btnAccess.postDelayed({
                        binding.btnAccess.doneLoadingAnimation(
                            R.color.blue_semi_dark,
                            BitmapFactory.decodeResource(
                                requireContext().resources,
                                R.drawable.cancel
                            )
                        )
                        binding.btnAccess.postDelayed({
                            binding.btnAccess.revertAnimation()
                        }, 700)
                        enableElements()

                        if (it.exeption.message.toString()
                                .contains(ErrorFirebase.UserError.errorFirebaseMessagge)
                        ) {
                            binding.layoutLogin.error = ErrorFirebase.UserError.errorMessaje


                        } else if (it.exeption.message.toString()
                                .contains(ErrorFirebase.PassError.errorFirebaseMessagge)
                        ) {
                            binding.layoutPass.error = ErrorFirebase.PassError.errorMessaje
                        } else if (it.exeption.message.toString()
                                .contains(ErrorFirebase.BlockError.errorFirebaseMessagge)
                        ) {
                            binding.layoutPass.error = ErrorFirebase.BlockError.errorMessaje
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Por favor revise la conexiona a internet",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }, 700)
                }

                is Resources.Loading -> {
                    disableElements()
                    binding.btnAccess.startAnimation()

                }

                is Resources.Success -> {
                    if (binding.cbRemember.isChecked) {
                        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                            loginViewModel.saveLocalUser(User(user, pass))
                        }
                    }
                    binding.btnAccess.postDelayed({
                        binding.btnAccess.doneLoadingAnimation(
                            R.color.blue_semi_dark,
                            BitmapFactory.decodeResource(
                                requireContext().resources,
                                R.drawable.check
                            )
                        )
                        binding.btnAccess.postDelayed({
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        }, 500)
                    }, 700)

                }
            }
        }
    }
}



