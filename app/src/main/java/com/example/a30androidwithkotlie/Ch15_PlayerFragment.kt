package com.example.a30androidwithkotlie

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a30androidwithkotlie.ch15_adapter.ch15_VideoAdapter
import com.example.a30androidwithkotlie.ch15_dto.ch15_VideoDto
import com.example.a30androidwithkotlie.ch15_service.ch15_VideoService
import com.example.a30androidwithkotlie.databinding.Ch15FragmentPlayerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

class Ch15_PlayerFragment : Fragment(R.layout.ch15_fragment_player) {

    private var binding : Ch15FragmentPlayerBinding? = null
    private lateinit var videoAdapter: ch15_VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = Ch15FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding

        initMotionLayoutEvent(fragmentPlayerBinding)
        initRecyclerView(fragmentPlayerBinding)

        getVideoList()
    }

    private fun initMotionLayoutEvent(fragmentPlayerBinding : Ch15FragmentPlayerBinding) {
        fragmentPlayerBinding.playerMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int
            ) {

            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float
            ) {
                binding?.let {
                    (activity as Ch15_MainActivity).also { mainActivity ->
                        mainActivity.findViewById<MotionLayout>(R.id.ch15_mainMotionLayout).progress = abs(progress)
                    }
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float
            ) {

            }

        })
    }

    private fun initRecyclerView(fragmentPlayerBinding : Ch15FragmentPlayerBinding) {

        videoAdapter = ch15_VideoAdapter(callback = { url, title ->
            play(url, title)
        })

        fragmentPlayerBinding.ch15FragmentRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }
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
//                            Log.d("MainActivity", "response fail")
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

    fun play(url : String, title : String) {
        binding?.let {
            it.playerMotionLayout.transitionToEnd()
            it.ch15BottomTitleTextView.text = title
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}