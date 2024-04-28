plugins {
    id(ProjectDependencies.Plugins.Android.android_library)
    kotlin(ProjectDependencies.Plugins.JetBrains.kotlin_android)
}

android {
    namespace = "${GradleConfig.namespace}.domain"
    compileSdk = GradleConfig.compile_sdk

    defaultConfig {
        minSdk = GradleConfig.min_sdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = GradleConfig.jvm_target
    }
}

dependencies {
    api(ProjectDependencies.Plugins.Dagger.inject)
    api(ProjectDependencies.Coroutines.coroutines)
}