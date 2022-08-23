//package com.example.a30androidwithkotlie.ch12_adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.a30androidwithkotlie.ch12_model.Ch12_Book
//import com.example.a30androidwithkotlie.databinding.Ch12ItemBookBinding
//
//class Ch12_BookAdapter(private val itemClickedListener : (Ch12_Book) -> Unit) : ListAdapter<Ch12_Book, Ch12_BookAdapter.BookItemViewHolder>(diffUtil) {
//
//    inner class BookItemViewHolder(private val binding : Ch12ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(bookModel : Ch12_Book) {
//            binding.titleTextView.text = bookModel.title
//            binding.descriptionTextView.text = bookModel.description
//
//            binding.root.setOnClickListener {
//                itemClickedListener(bookModel)
//            }
//
//            Glide
//                .with(binding.coverImageView.context)
//                .load(bookModel.coverSmallUrl)
//                .into(binding.coverImageView)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
//        return BookItemViewHolder(Ch12ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
//        holder.bind(currentList[position])
//    }
//
//    companion object {
//        val diffUtil = object : DiffUtil.ItemCallback<Ch12_Book>() {
//            override fun areItemsTheSame(oldItem: Ch12_Book, newItem: Ch12_Book): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: Ch12_Book, newItem: Ch12_Book): Boolean {
//                return oldItem.id == newItem.id
//            }
//        }
//    }
//}