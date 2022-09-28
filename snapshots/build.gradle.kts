@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.datikaa.snapshots"
    compileSdk = 32

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {

    implementation(project(":app"))
    implementation(libs.bundles.compose)
    testImplementation("junit:junit:4.13.2")
}

tasks.withType<Test>().configureEach {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(11))
    })
}