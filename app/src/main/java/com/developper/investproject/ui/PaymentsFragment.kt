package com.developper.investproject.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developper.investproject.R
import com.developper.investproject.`class`.Constants
import com.developper.investproject.databinding.FragmentPaymentsBinding

class PaymentsFragment : Fragment() {

    lateinit var binding: FragmentPaymentsBinding
    lateinit var preferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPaymentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = requireActivity().getSharedPreferences(Constants.baza, Context.MODE_PRIVATE)

        val telegram: String = preferences.getString(Constants.key_Telegram, null).toString()
        val summa: String = preferences.getString(Constants.key_Summa, null).toString()

        binding.txtAmount.setText(summa)
        binding.txtTelegramId.setText(telegram)
    }
}