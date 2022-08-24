package com.example.a30androidwithkotlie.ch15_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a30androidwithkotlie.R
import com.example.a30androidwithkotlie.ch15_model.Ch15_VideoModel

class ch15_VideoAdapter : ListAdapter<Ch15_VideoModel, ch15_VideoAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : Ch15_VideoModel) {
            val titleTextView = view.findViewById<TextView>(R.id.ch15_titleTextView)
            val subTitleTextView = view.findViewById<TextView>(R.id.ch15_subTitleTextView)
            val thumbnailImageView = view.findViewById<ImageView>(R.id.ch15_thumbnailImageView)

            titleTextView.text = item.title
            subTitleTextView.text = item.subTitle

            Glide.with(thumbnailImageView.context)
                .load(item.thumb)
                .into(thumbnailImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ch15_item_video, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Ch15_VideoModel>() {
            override fun areItemsTheSame(
                oldItem: Ch15_VideoModel,
                newItem: Ch15_VideoModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Ch15_VideoModel,
                newItem: Ch15_VideoModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}