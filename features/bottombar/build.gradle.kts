plugins {
    id(ProjectDependencies.Plugins.Android.android_library)
    kotlin(ProjectDependencies.Plugins.JetBrains.kotlin_android)
}

android {
    namespace = "${GradleConfig.namespace}.features.bottombar"
    compileSdk = GradleConfig.compile_sdk

    defaultConfig {
        minSdk = GradleConfig.min_sdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = GradleConfig.jvm_target
    }
}

dependencies {
    implementation(project(ProjectDependencies.FeatureModule.PATH to ProjectDependencies.FeatureModule.core))
}