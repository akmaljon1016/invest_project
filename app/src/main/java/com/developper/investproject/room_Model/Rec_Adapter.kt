package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developper.investproject.databinding.HistoryItemBinding
import com.developper.investproject.room_Model.Note

class Rec_Adapter : ListAdapter<Note, Rec_Adapter.MyViewHolder>(diffutill) {

    class MyViewHolder(val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var listener: OnItemClickListener? = null

    companion object {
        val diffutill = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.summa == newItem.summa
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.txtAmount.text = item.summa.toString()
        holder.binding.txtIdTg.text = item.telegram_id.toString()
        holder.binding.txtClock.text = item.time.toString()

//        holder.binding.btnDelete.setOnClickListener {
//            listener?.delete(note = item)
//        }
//        holder.binding.btnUpdate.setOnClickListener {
//            listener?.update(note = item)
//        }
    }

    interface OnItemClickListener {
        fun delete(note: Note)
        fun update(note: Note)

    }

    fun onItemClickListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }
}