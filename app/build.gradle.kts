@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}


android {
    compileSdk = 33
    namespace = "com.datikaa.themoviedbapp"

    defaultConfig {
        applicationId = "com.datikaa.themoviedbapp"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    val tmdbApiKey: String = gradleLocalProperties(rootDir).getProperty("tmdb_api_key")

    buildTypes.forEach {
        it.buildConfigField("String", "TMDB_API_KEY", tmdbApiKey)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.majorVersion
    }
}

dependencies {

    implementation(libs.koin)
    implementation(libs.coil)
    implementation(libs.moshi)

    ksp(libs.moshi.codegen)

    implementation(libs.androidx.core)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)

    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.constraintlayout)
    implementation(libs.activity.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.compose)

    implementation(libs.bundles.compose)
    implementation(libs.bundles.retrofit)
}
