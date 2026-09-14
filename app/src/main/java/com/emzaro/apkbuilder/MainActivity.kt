package com.emzaro.apkbuilder

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val bg = Color.BLACK
    private val card = Color.rgb(9, 15, 22)
    private val blue = Color.rgb(22, 131, 255)
    private val muted = Color.rgb(153, 168, 188)
    private val green = Color.rgb(24, 213, 138)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK

        // When a website URL is supplied to the build, this APK becomes a real
        // Web-to-Native Android wrapper around that HTTPS website.
        val url = BuildConfig.WEBSITE_URL.trim()
        if (url.isNotEmpty()) {
            openWebsite(url)
        } else {
            openBuilderHome()
        }
    }

    private fun openWebsite(url: String) {
        val webView = WebView(this).apply {
            setBackgroundColor(Color.BLACK)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.loadsImagesAutomatically = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.mediaPlaybackRequiresUserGesture = false
            settings.setSupportZoom(false)
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }
        setContentView(webView)
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        val view = window.decorView.findFocus()
        if (view is WebView && view.canGoBack()) {
            view.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun tv(text: String, size: Float, color: Int, bold: Boolean = false): TextView = TextView(this).apply {
        this.text = text
        textSize = size
        setTextColor(color)
        if (bold) typeface = Typeface.DEFAULT_BOLD
    }

    private fun cardButton(icon: String, title: String, subtitle: String, action: () -> Unit): Button = Button(this).apply {
        text = "$icon  $title\n$subtitle"
        textSize = 15f
        gravity = Gravity.CENTER_VERTICAL or Gravity.START
        setTextColor(Color.WHITE)
        setPadding(24, 12, 16, 12)
        setBackgroundColor(card)
        setOnClickListener { action() }
    }

    private fun openBuilderHome() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(22, 10, 22, 18)
        }

        val top = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        top.addView(tv("☰", 30f, Color.WHITE).apply { gravity = Gravity.CENTER }, LinearLayout.LayoutParams(0, 58, 1f))
        top.addView(Button(this).apply {
            text = "✦  Get Plus"
            textSize = 18f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.rgb(80, 170, 255))
            setBackgroundColor(Color.rgb(38, 49, 59))
            setPadding(18, 5, 18, 5)
        }, LinearLayout.LayoutParams(-2, 58))
        top.addView(tv("◔", 29f, Color.WHITE).apply { gravity = Gravity.CENTER }, LinearLayout.LayoutParams(0, 58, 1f))
        root.addView(top)

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        content.addView(tv("🤖", 54f, blue).apply { gravity = Gravity.CENTER; setPadding(0, 55, 0, 10) }, LinearLayout.LayoutParams(-1, 125))
        val brand = tv("APK ", 42f, Color.WHITE, true).apply { append(tvSpan("Builder", blue, true)); gravity = Gravity.CENTER }
        content.addView(brand)
        content.addView(tv("Turn your ideas into real Android apps\nwith AI. Fast. Easy. No coding.", 17f, muted).apply { gravity = Gravity.CENTER; setPadding(0, 12, 0, 28) })

        val quick = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        val row1 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        row1.addView(cardButton("✦", "Create App", "Describe your idea") {}, LinearLayout.LayoutParams(0, 82, 1f))
        row1.addView(cardButton("▧", "Use Template", "Start from a template") {}, LinearLayout.LayoutParams(0, 82, 1f).apply { leftMargin = 10 })
        quick.addView(row1)
        val row2 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        row2.addView(cardButton("🌐", "Web to APK", "Wrap any HTTPS website") { showWebUrlHelp() }, LinearLayout.LayoutParams(0, 82, 1f).apply { topMargin = 10 })
        row2.addView(cardButton("⚙", "App Settings", "Configure your app") {}, LinearLayout.LayoutParams(0, 82, 1f).apply { topMargin = 10; leftMargin = 10 })
        quick.addView(row2)
        content.addView(quick)

        content.addView(tv("Recent Projects", 27f, Color.WHITE, true).apply { setPadding(0, 34, 0, 14) })
        content.addView(tv("✓  Native Android build ready\n\n${BuildConfig.APP_DESCRIPTION}", 14f, Color.WHITE).apply {
            setPadding(18, 18, 18, 18)
            setBackgroundColor(Color.rgb(13, 18, 25))
        }, LinearLayout.LayoutParams(-1, -2).apply { topMargin = 18 })

        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
    }

    private fun showWebUrlHelp() {
        val message = "To create a Web-to-Native APK, run the GitHub Actions workflow and provide an HTTPS Website URL. The generated APK opens that website inside a native Android WebView with JavaScript and local storage enabled."
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.BLACK)
            setPadding(28, 28, 28, 28)
        }
        root.addView(tv("🌐  Web to Native APK", 28f, Color.WHITE, true))
        root.addView(tv(message, 16f, muted).apply { setPadding(0, 18, 0, 26) })
        root.addView(Button(this).apply {
            text = "Back to APK Builder"
            setOnClickListener { openBuilderHome() }
        })
        setContentView(root)
    }

    private fun tvSpan(text: String, color: Int, bold: Boolean): android.text.SpannableString {
        val s = android.text.SpannableString(text)
        s.setSpan(android.text.style.ForegroundColorSpan(color), 0, text.length, 0)
        if (bold) s.setSpan(android.text.style.StyleSpan(Typeface.BOLD), 0, text.length, 0)
        return s
    }
}
