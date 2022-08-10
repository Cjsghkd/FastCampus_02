package com.example.a30androidwithkotlie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class Ch13_MainActivity : AppCompatActivity() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch13_main)

    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser == null) { // 파이어베이스에 유저 정보가 널인가?
            startActivity(Intent(this, Ch13_LoginActivity::class.java))
        }
    }
}