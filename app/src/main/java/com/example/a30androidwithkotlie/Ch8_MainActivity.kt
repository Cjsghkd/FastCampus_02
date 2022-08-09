package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class Ch8_MainActivity : AppCompatActivity() {

    private val webview : WebView by lazy {
        findViewById(R.id.WebView)
    }

    private val addressBar : EditText by lazy {
        findViewById(R.id.addressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch8_main)

        initViews()
        bindViews()
    }

    private fun initViews() {
        webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("http://www.google.com")
        }
//        webview.webViewClient = WebViewClient()
//        webview.settings.javaScriptEnabled = true
//        webview.loadUrl("http://www.google.com")
    }

    private fun bindViews() {
        addressBar.setOnEditorActionListener { v, actionId , event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                webview.loadUrl(v.text.toString())
            }

            return@setOnEditorActionListener false
        }
    }

}