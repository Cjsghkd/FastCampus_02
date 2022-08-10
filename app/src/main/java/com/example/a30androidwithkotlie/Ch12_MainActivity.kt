package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a30androidwithkotlie.ch12_adapter.Ch12_BookAdapter
import com.example.a30androidwithkotlie.ch12_api.Ch12_BookService
import com.example.a30androidwithkotlie.ch12_model.Ch12_BastSellerDto
import com.example.a30androidwithkotlie.databinding.ActivityCh12MainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Ch12_MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCh12MainBinding
    private lateinit var adapter : Ch12_BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCh12MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(Ch12_BookService::class.java)

        bookService.getBestSellerBooks("CCFDCE1728A7F7C946AF7D6820A3BB7B353505566802F1FE462287F271B43AFC")
            .enqueue(object : Callback<Ch12_BastSellerDto> {
                override fun onResponse(
                    call: Call<Ch12_BastSellerDto>, response: Response<Ch12_BastSellerDto>) {
                    // 성공처리
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT!! SUCCESS")
                        return
                    }

                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                        adapter.submitList(it.books)
                    }
                }

                override fun onFailure(call: Call<Ch12_BastSellerDto>, t: Throwable) {
                    // 실패처리
                    Log.e(TAG, t.toString())
                }
            })
    }

    fun initBookRecyclerView() {
        adapter = Ch12_BookAdapter()

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}