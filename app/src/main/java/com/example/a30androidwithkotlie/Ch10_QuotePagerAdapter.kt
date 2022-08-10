package com.example.a30androidwithkotlie

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Ch10_QuotePagerAdapter(
    private val quotes : List<Ch10_Quote>,
    private val isNameRevealed : Boolean
) : RecyclerView.Adapter<Ch10_QuotePagerAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val quoteTextView : TextView = itemView.findViewById(R.id.quoteTextView)
        private val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(quote : Ch10_Quote, isNameRevealed: Boolean) {
            quoteTextView.text = quote.quote
            if (isNameRevealed) {
                nameTextView.text = quote.name
                nameTextView.visibility = View.VISIBLE
            } else {
                nameTextView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ch10_item_quote, parent, false)
        )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quotes[position], isNameRevealed)
    }

    override fun getItemCount() = quotes.size
}