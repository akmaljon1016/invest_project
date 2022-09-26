package com.developper.investproject.ui.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developper.investproject.R
import com.developper.investproject.databinding.FragmentHistory2Binding
import com.developper.investproject.room_Model.Note
import com.example.room.NoteDataBase
import com.example.room.Rec_Adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HistoryFragment2 : Fragment() {

    lateinit var binding: FragmentHistory2Binding
    lateinit var adapter: Rec_Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHistory2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.mealTypeChipGroup.setOnCheckedChangeListener{group,checkedId ->
//            when (checkedId){
//                R.id.chipClose ->{
//                    findNavController().navigate(R.id.action_historyFragment2_to_historyFragment)
//                }
//                R.id.chipTrade2 -> {
//                    findNavController().navigate(R.id.action_historyFragment_to_historyFragment3)
//                }
//                R.id.chipInvest -> {
//                    findNavController().navigate(R.id.action_historyFragment_to_historyFragment4)
//                }
//            }
//        }

                adapter = Rec_Adapter()

        adapter.onItemClickListener(object : Rec_Adapter.OnItemClickListener {
            override fun delete(note: Note) {
                GlobalScope.launch(Dispatchers.IO) {
                    deleteNote(note)
                    setList()
                }
            }

            override fun update(note: Note) {
            }
        })

        GlobalScope.launch(Dispatchers.IO) {
            setList()
            binding.recyclerview.adapter = adapter
        }

    }
    fun setList() {
        val notes =
            NoteDataBase.DatabaseBuilder.getDatabase(requireContext()).noteDao().getAllNotes()
        adapter.submitList(notes)
    }

    fun deleteNote(note: Note) {
        NoteDataBase.DatabaseBuilder.getDatabase(requireContext()).noteDao().deleteNote(note)
    }

    }
