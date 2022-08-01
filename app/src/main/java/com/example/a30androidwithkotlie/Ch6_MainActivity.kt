package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView

class Ch6_MainActivity : AppCompatActivity() {

    private val remainMinuteTextView : TextView by lazy {
        findViewById(R.id.remainMinutesTextView)
    }

    private val remainSecondsTextView : TextView by lazy {
        findViewById(R.id.remainSecondsTextView)
    }

    private val seekBar : SeekBar by lazy {
        findViewById(R.id.seekBar)
    }

    private var currentCountDownTimer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch6_main)

        bindViews()

    }

    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar : SeekBar?, progress : Int, fromUser : Boolean) {
                    if (fromUser) {
                        updateRemainTime(progress * 60 * 1000L)
                    }
                }

                override fun onStartTrackingTouch(seekBar : SeekBar?) {
                    currentCountDownTimer?.cancel()
                    currentCountDownTimer = null
                }

                override fun onStopTrackingTouch(seekBar : SeekBar?) {
                    seekBar ?: return // 좌측 값이 널이면 리턴한다
                    currentCountDownTimer = createCountDownTimer(seekBar.progress * 60 * 1000L)
                        currentCountDownTimer?.start()
                }
            }
        )
    }

    private fun createCountDownTimer(initialMillis : Long) =
        object  : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(millisUntilFinished : Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekBar(millisUntilFinished)
            }

            override fun onFinish() {
                updateRemainTime(0)
                updateSeekBar(0)
            }
        }

    private fun updateRemainTime(remainMills : Long) {

        val remainSeconds = remainMills / 1000

        remainMinuteTextView.text = "%02d".format(remainSeconds / 60)
        remainSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMills : Long) {
        seekBar.progress = (remainMills / 1000 / 60).toInt()
    }

}