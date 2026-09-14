plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val webUrl = providers.gradleProperty("webUrl").orElse("https://emzaro731-byte.github.io/hhv/").get()
val appVersion = providers.gradleProperty("versionName").orElse("1.0.0").get()

android {
    namespace = "com.emzaro.apkbuilder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.emzaro.apkbuilder"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = appVersion
        buildConfigField("String", "WEB_URL", "\"${webUrl.replace("\\", "\\\\").replace("\"", "\\\"")}\"")
    }

    buildTypes {
        release { isMinifyEnabled = false }
    }

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
}
