package com.jptechnology.appestacaohack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        // Set JavaScript execution
        wbvWeb.settings.javaScriptEnabled = true

        // Load website link
        wbvWeb.loadUrl("http://br.cellep.com/estacaohack")

        // Define WebView as standard web client
        wbvWeb.webViewClient = WebViewClient()
    }
}