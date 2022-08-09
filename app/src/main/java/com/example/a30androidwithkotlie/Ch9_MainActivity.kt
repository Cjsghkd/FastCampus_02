package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class Ch9_MainActivity : AppCompatActivity() {

    private val resultTextView : TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private val firebaseToken : TextView by lazy {
        findViewById(R.id.FirebaseTokenTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch9_main)
    }
}