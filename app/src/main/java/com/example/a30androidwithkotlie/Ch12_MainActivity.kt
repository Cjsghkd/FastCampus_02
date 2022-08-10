package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a30androidwithkotlie.ch12_adapter.Ch12_BookAdapter
import com.example.a30androidwithkotlie.ch12_api.Ch12_BookService
import com.example.a30androidwithkotlie.ch12_model.Ch12_BastSellerDto
import com.example.a30androidwithkotlie.ch12_model.Ch12_SearchBookDto
import com.example.a30androidwithkotlie.databinding.ActivityCh12MainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Ch12_MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCh12MainBinding
    private lateinit var adapter : Ch12_BookAdapter
    private lateinit var bookService : Ch12_BookService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCh12MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(Ch12_BookService::class.java)

        bookService.getBestSellerBooks(getString(R.string.interparkAPIKey))
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

        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        }
    }

    private fun search(keyword : String) {
        bookService.getBooksByName(getString(R.string.interparkAPIKey), keyword)
            .enqueue(object : Callback<Ch12_SearchBookDto> {
                override fun onResponse(
                    call: Call<Ch12_SearchBookDto>, response: Response<Ch12_SearchBookDto>) {
                    // 성공처리
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT!! SUCCESS")
                        return
                    }

                    adapter.submitList(response.body()?.books.orEmpty())

                }

                override fun onFailure(call: Call<Ch12_SearchBookDto>, t: Throwable) {
                    // 실패처리
                    Log.e(TAG, t.toString())
                }
            })
    }

    private fun initBookRecyclerView() {
        adapter = Ch12_BookAdapter()

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}