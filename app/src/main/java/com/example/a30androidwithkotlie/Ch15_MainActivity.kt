package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Ch15_MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch15_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.ch15_fragmentContainer, Ch15_PlayerFragment())
            .commit()

    }
}