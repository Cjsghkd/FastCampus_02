package com.example.a30androidwithkotlie.ch12_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a30androidwithkotlie.ch12_model.Ch12_History
import com.example.a30androidwithkotlie.databinding.Ch12ItemHistoryBinding

class Ch12_HistoryAdapter(val historyDeleteClickedListener : (String) -> Unit) : ListAdapter<Ch12_History, Ch12_HistoryAdapter.HistoryItemViewHolder>(diffUtil) {

    inner class HistoryItemViewHolder(private val binding : Ch12ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyModel : Ch12_History) {
            binding.historyKeywordTextView.text = historyModel.keyword

            binding.historyKeywordDeleteButton.setOnClickListener {
                historyDeleteClickedListener(historyModel.keyword.orEmpty())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        return HistoryItemViewHolder(Ch12ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Ch12_History>() {
            override fun areItemsTheSame(oldItem: Ch12_History, newItem: Ch12_History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Ch12_History, newItem: Ch12_History): Boolean {
                return oldItem.keyword == newItem.keyword
            }
        }
    }
}