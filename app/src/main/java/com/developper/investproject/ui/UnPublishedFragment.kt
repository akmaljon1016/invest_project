package com.developper.investproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developper.investproject.R
import com.developper.investproject.databinding.FragmentUnPublishedBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UnPublishedFragment : BottomSheetDialogFragment() {
 lateinit var binding: FragmentUnPublishedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUnPublishedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.btnClose.setOnClickListener {
    findNavController().navigate(R.id.action_unPublishedFragment_to_destination_exchange)
}
    }
}