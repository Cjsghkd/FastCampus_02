package com.example.a30androidwithkotlie

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class Ch3_DiaryActivity : AppCompatActivity() {

    private val diaryEditText : EditText by lazy {
        findViewById(R.id.diaryEditText)
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch3_diary)

//      val diaryEditText : EditText = findViewById(R.id.diaryEditText) 이렇게 써도 된다
        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail", ""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail", diaryEditText.text.toString())
            }
            Log.d("DiaryActivity", "Save!! ${diaryEditText.text.toString()}")
        }

        diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity", "TextChanged :: ${it}")
            handler.removeCallbacks(runnable) // 이전에 있던 것을 지우기
            handler.postDelayed(runnable, 500)
        }


    }
}