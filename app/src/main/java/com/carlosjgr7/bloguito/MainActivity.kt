package com.carlosjgr7.bloguito

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.fragment.NavHostFragment
import com.carlosjgr7.bloguito.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by preferencesDataStore(name = "CONFIGURATIONSDATASTORE")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        if (navController.currentDestination?.id == R.id.loginFragment ||
            navController.currentDestination?.id == R.id.presentationFragment ||
            navController.currentDestination?.id == R.id.mainFragment
        ) {
            finish()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }


}


