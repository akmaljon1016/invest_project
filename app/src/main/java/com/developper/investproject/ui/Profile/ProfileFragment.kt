package com.developper.investproject.ui.Profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developper.investproject.R
import com.developper.investproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
binding=FragmentProfileBinding.inflate(layoutInflater)
    return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddVilla.setOnClickListener {
            findNavController().navigate(R.id.action_destination_profile_to_editProfileFragment)
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_historyFragment)
        }

        binding.btnTelegram.setOnClickListener {
            val telegramIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://t.me/NB7_Channel")
            }
            startActivity(telegramIntent)
        }

        binding.btnHelpUser.setOnClickListener {
            findNavController().navigate(R.id.action_destination_profile_to_profileHelpFragment)
        }

    }
}