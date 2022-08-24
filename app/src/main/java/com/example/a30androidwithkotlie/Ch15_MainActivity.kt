package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a30androidwithkotlie.ch15_adapter.ch15_VideoAdapter
import com.example.a30androidwithkotlie.ch15_dto.ch15_VideoDto
import com.example.a30androidwithkotlie.ch15_service.ch15_VideoService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Ch15_MainActivity : AppCompatActivity() {

    private lateinit var videoAdapter: ch15_VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch15_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.ch15_fragmentContainer, Ch15_PlayerFragment())
            .commit()

        videoAdapter = ch15_VideoAdapter()

        findViewById<RecyclerView>(R.id.ch15_mainRecyclerView).apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }

        getVideoList()
    }

    private fun getVideoList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ch15_VideoService::class.java).also {
            it.listVideos()
                .enqueue(object : Callback<ch15_VideoDto> {
                    override fun onResponse(call: Call<ch15_VideoDto>, response: Response<ch15_VideoDto>) {
                        if (response.isSuccessful.not()) {
                            Log.d("MainActivity", "response fail")
                            return
                        }

                        response.body()?.let { videoDto ->
//                            Log.d("MainActivity", it.toString())
                            videoAdapter.submitList(videoDto.videos)
                        }


                    }

                    override fun onFailure(call: Call<ch15_VideoDto>, t: Throwable) {
                        // 예외처리
                    }

                })
        }
    }
}