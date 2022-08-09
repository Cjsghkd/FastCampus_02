package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class Ch10_MainActivity : AppCompatActivity() {

    private val viewPage : ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch10_main)

        initViews()
    }

    private fun initViews() {
        viewPage.adapter = Ch10_QuotePagerAdapter(emptyList())
    }
}