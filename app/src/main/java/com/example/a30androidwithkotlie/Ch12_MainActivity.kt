package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a30androidwithkotlie.ch12_api.Ch12_BookService
import com.example.a30androidwithkotlie.ch12_model.Ch12_BastSellerDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Ch12_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch12_main)

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

                    }
                }

                override fun onFailure(call: Call<Ch12_BastSellerDto>, t: Throwable) {
                    // 실패처리
                    Log.e(TAG, t.toString())
                }
            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}