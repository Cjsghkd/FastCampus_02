package com.example.a30androidwithkotlie.ch14_home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a30androidwithkotlie.R
import com.example.a30androidwithkotlie.databinding.Ch14FragmentHomeBinding

class Ch14_HomeFragment : Fragment(R.layout.ch14_fragment_home) {

    private var binding : Ch14FragmentHomeBinding? = null
    private lateinit var articleAdapter : Ch14_ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = Ch14FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        articleAdapter = Ch14_ArticleAdapter()
        articleAdapter.submitList(mutableListOf<Ch14_ArticleModel>().apply {
            add(Ch14_ArticleModel("0", "aaaa", 10000, "5000원", ""))
            add(Ch14_ArticleModel("0", "bbbb", 20000, "10000원", ""))
        })

        fragmentHomeBinding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentHomeBinding.articleRecyclerView.adapter = articleAdapter


    }

}