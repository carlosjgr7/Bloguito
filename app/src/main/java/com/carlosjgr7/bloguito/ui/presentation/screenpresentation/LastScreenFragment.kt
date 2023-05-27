package com.carlosjgr7.bloguito.ui.presentation.screenpresentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.carlosjgr7.bloguito.MainActivity
import com.carlosjgr7.bloguito.R
import com.carlosjgr7.bloguito.dataStore
import com.carlosjgr7.bloguito.databinding.FragmentLastScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LastScreenFragment() : Fragment(R.layout.fragment_last_screen) {

    private lateinit var binding:FragmentLastScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLastScreenBinding.bind(view)

        binding.btnToLogin.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                setViewd()
                withContext(Dispatchers.Main){
                    findNavController().navigate(R.id.action_presentationFragment_to_loginFragment)

                }
            }

        }


    }

    private suspend fun setViewd() {
        context?.dataStore?.edit {
            it[booleanPreferencesKey("PRESENTATION_VIWED")] = true
        }
    }
}