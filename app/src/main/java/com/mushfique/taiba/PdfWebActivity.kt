package com.mushfique.taiba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pdf_web.*

class PdfWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_web)

        val url = intent.getStringExtra("link")
        val finalurl = "http://drive.google.com/viewerng/viewer?embedded=true&url=$url"
        webview_pdf.settings.javaScriptEnabled = true
        webview_pdf.settings.builtInZoomControls = true
        webview_pdf.loadUrl(finalurl)
    finish()
    }
}




/*webview_pdf.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url:String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webview_pdf.loadUrl("$inturl")
        webview_pdf.settings.javaScriptEnabled = true
        webview_pdf.settings.allowContentAccess = true
        webview_pdf.settings.domStorageEnabled = true
        webview_pdf.settings.useWideViewPort = true
        webview_pdf.settings.setAppCacheEnabled(true)*/