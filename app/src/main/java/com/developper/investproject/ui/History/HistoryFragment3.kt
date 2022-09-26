package com.developper.investproject.ui.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developper.investproject.R
import com.developper.investproject.databinding.FragmentHistory3Binding


class HistoryFragment3 : Fragment() {
lateinit var binding: FragmentHistory3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHistory3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mealTypeChipGroup.setOnCheckedChangeListener{group,checkedId ->
            when (checkedId){
                R.id.chipClose ->{
                    findNavController().navigate(R.id.action_historyFragment3_to_historyFragment)
                }
                R.id.chipTrade2 -> {
                    findNavController().navigate(R.id.action_historyFragment_to_historyFragment3)
                }
                R.id.chipInvest -> {
                    findNavController().navigate(R.id.action_historyFragment_to_historyFragment4)
                }
            }
        }


        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}