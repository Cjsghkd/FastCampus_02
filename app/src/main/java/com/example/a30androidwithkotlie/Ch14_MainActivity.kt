package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.a30androidwithkotlie.ch14_chatlist.Ch14_ChatListFragment
import com.example.a30androidwithkotlie.ch14_home.Ch14_HomeFragment
import com.example.a30androidwithkotlie.ch14_mypage.Ch14_MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Ch14_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch14_main)

        val homeFragment = Ch14_HomeFragment()
        val chatFragment = Ch14_ChatListFragment()
        val myPageFragment = Ch14_MyPageFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        replaceFragment(homeFragment) // 초기(처음 킬때)에는 홈 프래그먼트로

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.chatList -> replaceFragment(chatFragment)
                R.id.myPage -> replaceFragment(myPageFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}