package com.emzaro.apkbuilder

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val bg = Color.rgb(7, 7, 10)
    private val panel = Color.rgb(17, 18, 24)
    private val blue = Color.rgb(77, 141, 255)
    private val white = Color.WHITE
    private val muted = Color.rgb(150, 153, 164)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(28, 24, 28, 24)
        }

        val header = TextView(this).apply {
            text = "✦  ${BuildConfig.APP_NAME}"
            textSize = 23f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(white)
            setPadding(0, 12, 0, 20)
        }
        root.addView(header)

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val badge = TextView(this).apply {
            text = "NATIVE ANDROID  •  v${BuildConfig.VERSION_NAME}"
            textSize = 11f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.rgb(121, 170, 255))
            setPadding(0, 10, 0, 14)
        }
        content.addView(badge)

        val title = TextView(this).apply {
            text = "Built for your idea."
            textSize = 34f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(white)
            setPadding(0, 6, 0, 10)
        }
        content.addView(title)

        val description = TextView(this).apply {
            text = BuildConfig.APP_DESCRIPTION
            textSize = 16f
            setTextColor(muted)
            setPadding(0, 0, 0, 24)
        }
        content.addView(description)

        val status = TextView(this).apply {
            text = "✓  Native Android build is running"
            textSize = 15f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(white)
            setBackgroundColor(panel)
            setPadding(20, 20, 20, 20)
        }
        content.addView(status)

        val features = listOf(
            "⚡ Fast native Android performance",
            "🔒 Ready for secure API integration",
            "🔔 Notifications and background features",
            "💳 Payments and authentication can be added"
        )
        features.forEach { feature ->
            val row = TextView(this).apply {
                text = feature
                textSize = 14f
                setTextColor(Color.rgb(220, 222, 228))
                setPadding(18, 18, 18, 18)
            }
            content.addView(row)
        }

        val action = Button(this).apply {
            text = "Get Started"
            textSize = 15f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(white)
            setBackgroundColor(blue)
            setPadding(20, 8, 20, 8)
            setOnClickListener {
                status.text = "✓  Welcome to ${BuildConfig.APP_NAME}!\n\nYour native Android application is ready."
            }
        }
        val params = LinearLayout.LayoutParams(-1, 58)
        params.topMargin = 20
        content.addView(action, params)

        val footer = TextView(this).apply {
            text = "Built with APK Builder Hub"
            textSize = 12f
            setTextColor(Color.rgb(105, 108, 118))
            gravity = Gravity.CENTER
            setPadding(0, 36, 0, 20)
        }
        content.addView(footer)

        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
    }
}
