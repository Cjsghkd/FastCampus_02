package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class Ch8_MainActivity : AppCompatActivity() {

    private val webview : WebView by lazy {
        findViewById(R.id.WebView)
    }

    private val addressBar : EditText by lazy {
        findViewById(R.id.addressBar)
    }

    private val goHomeButton : ImageButton by lazy {
        findViewById(R.id.goHomeButton)
    }

    private val goBackButton : ImageButton by lazy {
        findViewById(R.id.goBackButton)
    }

    private val goForwardButton : ImageButton by lazy {
        findViewById(R.id.goForwardButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch8_main)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun initViews() {
        webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
//            loadUrl("http://www.google.com")
            loadUrl(DEFAULT_URL)
        }
//        webview.webViewClient = WebViewClient()
//        webview.settings.javaScriptEnabled = true
//        webview.loadUrl("http://www.google.com")
    }

    private fun bindViews() {
        goHomeButton.setOnClickListener {
//            webview.loadUrl("http://www.google.com")
            webview.loadUrl(DEFAULT_URL)
        }

        addressBar.setOnEditorActionListener { v, actionId , event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                webview.loadUrl(v.text.toString())
            }

            return@setOnEditorActionListener false
        }

        goBackButton.setOnClickListener {
            webview.goBack()
        }

        goForwardButton.setOnClickListener {
            webview.goForward()
        }
    }

    companion object {
        private const val DEFAULT_URL = "http://www.google.com"
    }

}