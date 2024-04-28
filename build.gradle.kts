// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ProjectDependencies.Plugins.Android.gradle_version apply false
    id("com.android.library") version ProjectDependencies.Plugins.Android.gradle_version apply false
    id("com.google.dagger.hilt.android") version ProjectDependencies.Plugins.Dagger.dagger_hilt_version apply false
    kotlin("android") version ProjectDependencies.Plugins.JetBrains.kotlin_version apply false
    kotlin("kapt") version ProjectDependencies.Plugins.JetBrains.kotlin_version apply false
    kotlin("jvm") version ProjectDependencies.Plugins.JetBrains.kotlin_version apply false
}