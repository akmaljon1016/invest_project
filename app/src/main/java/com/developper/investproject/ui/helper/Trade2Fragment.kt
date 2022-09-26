package com.developper.investproject.ui.helper

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.developper.investproject.R
import com.developper.investproject.`class`.Constants
import com.developper.investproject.databinding.FragmentTrade2Binding
import com.developper.investproject.room_Model.Trade.Note_trade
import com.example.room.NoteDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Trade2Fragment : Fragment() {

    lateinit var binding: FragmentTrade2Binding
    lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrade2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = requireActivity().getSharedPreferences(Constants.baza, Context.MODE_PRIVATE)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_trade2Fragment_to_destination_exchange)
        }
        binding.edSumma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var summa = 0
                if (!p0.isNullOrEmpty()) {
                    summa = p0.toString().toInt()
                }
                if (summa < 1000) {
                    binding.txtMinimum.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                } else {
                    binding.txtMinimum.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey
                        )
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnSave.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val summa = binding.edSumma.text.toString().toInt()
                val tg_id = binding.idTg.text.toString()
                val time = binding.edClock.text.toString()
                val noteTrade = Note_trade(0, summa, tg_id, time)
                NoteDataBase.DatabaseBuilder.getDatabase(requireContext()).noteTrade()
                    .insertTrade(noteTrade)
                val myEdit = preferences.edit()
                myEdit.putString(Constants.key_Telegram, tg_id)
                myEdit.putString(Constants.key_Summa, summa.toString())
                myEdit.apply()
            }
            findNavController().navigate(R.id.action_trade2Fragment_to_paymentsFragment)
        }
    }
}