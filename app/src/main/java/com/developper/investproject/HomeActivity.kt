package com.developper.investproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.developper.investproject.`class`.Constants
import com.developper.investproject.`class`.Constants.Companion.key_User
import com.developper.investproject.databinding.ActivityHomeBinding
import com.developper.investproject.databinding.FragmentInvetmentBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController=findNavController(R.id.container)
        binding.bottomNavigationMenu.setupWithNavController(navController)
    }
}







