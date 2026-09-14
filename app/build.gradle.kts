plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.emzaro.apkbuilder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.emzaro.apkbuilder"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release { isMinifyEnabled = false }
    }

    buildFeatures { buildConfig = true }

    defaultConfig {
        buildConfigField("String", "WEB_URL", "\"https://emzaro731-byte.github.io/hhv/\"")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
}
