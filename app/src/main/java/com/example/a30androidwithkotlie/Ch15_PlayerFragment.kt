package com.example.a30androidwithkotlie

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.example.a30androidwithkotlie.databinding.Ch15FragmentPlayerBinding
import kotlin.math.abs

class Ch15_PlayerFragment : Fragment(R.layout.ch15_fragment_player) {

    private var binding : Ch15FragmentPlayerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = Ch15FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding

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

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}