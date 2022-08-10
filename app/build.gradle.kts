import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.parcelize)
}


android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.datikaa.themoviedbapp"
        minSdk = 27
        targetSdk = 32
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0-rc02"
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
