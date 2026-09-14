package com.emzaro.apkbuilder

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 48, 32, 32)
            setBackgroundColor(Color.rgb(7, 10, 18))
        }

        val title = TextView(this).apply {
            text = BuildConfig.APP_NAME
            textSize = 30f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 16)
        }

        val description = TextView(this).apply {
            text = BuildConfig.APP_DESCRIPTION
            textSize = 17f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(8, 8, 8, 28)
        }

        val content = TextView(this).apply {
            text = "This is a real native Android app built with Kotlin.\n\nYour app can now be extended with native screens, authentication, payments, APIs, notifications and other Android features."
            textSize = 16f
            setTextColor(Color.WHITE)
            setPadding(20, 24, 20, 24)
        }

        val action = Button(this).apply {
            text = "Get Started"
            setOnClickListener { content.text = "Welcome to ${BuildConfig.APP_NAME}!\n\nThe native Android build is working successfully." }
        }

        val scroll = ScrollView(this).apply {
            addView(LinearLayout(this@MainActivity).apply {
                orientation = LinearLayout.VERTICAL
                addView(title)
                addView(description)
                addView(content)
                addView(action)
            })
        }

        root.addView(scroll, LinearLayout.LayoutParams(-1, -1))
        setContentView(root)
    }
}
