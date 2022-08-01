package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class Ch6_MainActivity : AppCompatActivity() {

    private val remainMinuteTextView : TextView by lazy {
        findViewById(R.id.remainMinutesTextView)
    }

    private val seekBar : SeekBar by lazy {
        findViewById(R.id.seekBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch6_main)

        bindViews()

    }

    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar : SeekBar?, progress : Int, fromUser : Boolean) {
                    remainMinuteTextView.text = "%02d".format(progress)
                }

                override fun onStartTrackingTouch(seekBar : SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar : SeekBar?) {

                }
            }
        )
    }

}