// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.gradle.catalog.updater)
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val nonStableKeywords = listOf("beta", "alpha", "snapshot")
    return nonStableKeywords.none { version.toLowerCase().contains(it) }
}
