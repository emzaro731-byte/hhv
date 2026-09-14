package com.emzaro.apkbuilder

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(22, 10, 22, 18)
        }

        val top = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val menu = tv("☰", 30f, Color.WHITE).apply { gravity = Gravity.CENTER; setPadding(8, 8, 20, 8) }
        val plus = Button(this).apply {
            text = "✦  Get Plus"
            textSize = 18f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.rgb(80, 170, 255))
            setBackgroundColor(Color.rgb(38, 49, 59))
            setPadding(18, 5, 18, 5)
        }
        top.addView(menu, LinearLayout.LayoutParams(0, 58, 1f))
        top.addView(plus, LinearLayout.LayoutParams(-2, 58))
        val profile = tv("◔", 29f, Color.WHITE).apply { gravity = Gravity.CENTER; setPadding(20, 8, 4, 8) }
        top.addView(profile, LinearLayout.LayoutParams(0, 58, 1f))
        root.addView(top)

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val logo = tv("🤖", 54f, blue).apply { gravity = Gravity.CENTER; setPadding(0, 55, 0, 10) }
        content.addView(logo, LinearLayout.LayoutParams(-1, 125))

        val brand = tv("APK ", 42f, Color.WHITE, true).apply { append(tvSpan("Builder", blue, true)) }
        brand.gravity = Gravity.CENTER
        content.addView(brand)

        val tagline = tv("Turn your ideas into real Android apps\nwith AI. Fast. Easy. No coding.", 17f, muted).apply {
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 28)
        }
        content.addView(tagline)

        val quick = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        val row1 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; weightSum = 2f }
        row1.addView(cardButton("✦", "Create App", "Describe your idea") { status.text = "Describe your app idea below to start building." }, LinearLayout.LayoutParams(0, 82, 1f))
        row1.addView(cardButton("▧", "Use Template", "Start from a template") { status.text = "Template library is ready." }, LinearLayout.LayoutParams(0, 82, 1f).apply { leftMargin = 10 })
        quick.addView(row1)
        val row2 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; weightSum = 2f }
        row2.addView(cardButton("</>", "Upload Code", "Use your own project") { status.text = "Native project import is available through the build pipeline." }, LinearLayout.LayoutParams(0, 82, 1f).apply { topMargin = 10 })
        row2.addView(cardButton("⚙", "App Settings", "Configure your app") { status.text = "${BuildConfig.APP_NAME} • ${BuildConfig.VERSION_NAME}" }, LinearLayout.LayoutParams(0, 82, 1f).apply { topMargin = 10; leftMargin = 10 })
        quick.addView(row2)
        content.addView(quick, LinearLayout.LayoutParams(-1, -2))

        val recent = tv("Recent Projects", 27f, Color.WHITE, true).apply { setPadding(0, 34, 0, 14) }
        content.addView(recent)

        fun project(icon: String, name: String, pkg: String, state: String, stateColor: Int): LinearLayout {
            val box = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL; setPadding(16, 13, 16, 13); setBackgroundColor(Color.rgb(10, 15, 21)) }
            val ic = tv(icon, 25f, Color.WHITE).apply { gravity = Gravity.CENTER; setBackgroundColor(Color.rgb(45, 110, 230)) }
            box.addView(ic, LinearLayout.LayoutParams(58, 58))
            val info = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(14, 0, 8, 0) }
            info.addView(tv(name, 17f, Color.WHITE, true))
            info.addView(tv(pkg, 13f, muted))
            info.addView(tv("Built recently", 13f, muted))
            box.addView(info, LinearLayout.LayoutParams(0, -2, 1f))
            box.addView(tv(state, 13f, stateColor, true).apply { gravity = Gravity.CENTER; setPadding(12, 10, 12, 10) }, LinearLayout.LayoutParams(-2, -2))
            return box
        }
        content.addView(project("🛍", "My Store App", "com.mystore.app", "✓ Ready", green), LinearLayout.LayoutParams(-1, 78).apply { bottomMargin = 10 })
        content.addView(project("💬", "Chat App", "com.chat.app", "◯ Building", blue), LinearLayout.LayoutParams(-1, 78).apply { bottomMargin = 10 })
        content.addView(project("♫", "Music Player", "com.music.player", "✓ Ready", green), LinearLayout.LayoutParams(-1, 78).apply { bottomMargin = 10 })

        val status = tv("✓  Native Android build ready\n\n${BuildConfig.APP_DESCRIPTION}", 14f, Color.WHITE).apply {
            setPadding(18, 18, 18, 18)
            setBackgroundColor(Color.rgb(13, 18, 25))
        }
        content.addView(status, LinearLayout.LayoutParams(-1, -2).apply { topMargin = 18 })

        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
    }

    private fun tvSpan(text: String, color: Int, bold: Boolean): android.text.SpannableString {
        val s = android.text.SpannableString(text)
        s.setSpan(android.text.style.ForegroundColorSpan(color), 0, text.length, 0)
        if (bold) s.setSpan(android.text.style.StyleSpan(Typeface.BOLD), 0, text.length, 0)
        return s
    }
}
