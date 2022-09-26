package com.developper.investproject.ui.Exchange

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.developper.investproject.R
import com.developper.investproject.databinding.FragmentExchangeBinding

class ExchangeFragment : Fragment() {

    lateinit var binding: FragmentExchangeBinding
    private lateinit var viewModel: ExchangeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExchangeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnInvest.setOnClickListener {
            findNavController().navigate(R.id.action_destination_exchange_to_investFragment)
            Toast.makeText(requireActivity(), "Приятного инвестиций", Toast.LENGTH_SHORT).show()
        }
        binding.btnTradeSmall.setOnClickListener {
            findNavController().navigate(R.id.action_destination_exchange_to_trade1Fragment)
            Toast.makeText(requireActivity(), "Приятного торговля", Toast.LENGTH_SHORT).show()
        }
        binding.btnTradeBig.setOnClickListener {
            findNavController().navigate(R.id.action_destination_exchange_to_trade2Fragment)
            Toast.makeText(requireActivity(), "Приятного торговля", Toast.LENGTH_SHORT).show()
        }
    }

}