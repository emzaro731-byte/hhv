plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val appVersion = providers.gradleProperty("versionName").orElse("1.0.0").get()
val appName = providers.gradleProperty("appName").orElse("My Native App").get()
val appPackage = providers.gradleProperty("appPackage").orElse("com.example.myapp").get()
val appDescription = providers.gradleProperty("appDescription").orElse("Built with APK Builder Hub").get()
val websiteUrl = providers.gradleProperty("websiteUrl").orElse("").get()
val orientation = providers.gradleProperty("orientation").orElse("portrait").get()

fun quote(value: String): String = "\"${value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").replace("\r", " ")}\""

android {
    namespace = "com.emzaro.apkbuilder"
    compileSdk = 35

    defaultConfig {
        applicationId = appPackage
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = appVersion
        buildConfigField("String", "APP_NAME", quote(appName))
        buildConfigField("String", "APP_DESCRIPTION", quote(appDescription))
        buildConfigField("String", "WEBSITE_URL", quote(websiteUrl))
        manifestPlaceholders["appName"] = appName
        manifestPlaceholders["appOrientation"] = when (orientation.lowercase()) {
            "landscape" -> "landscape"
            "sensor" -> "fullSensor"
            else -> "portrait"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions { jvmTarget = "17" }

    buildTypes {
        release { isMinifyEnabled = false }
    }

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
}
