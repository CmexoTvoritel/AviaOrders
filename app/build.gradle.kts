plugins {
    id(ProjectDependencies.Plugins.Android.android_application)
    id(ProjectDependencies.Plugins.Dagger.dagger_hilt)
    kotlin(ProjectDependencies.Plugins.JetBrains.kotlin_android)
    kotlin(ProjectDependencies.Plugins.JetBrains.kotlin_kapt)
}

android {
    namespace = GradleConfig.application_id
    compileSdk = GradleConfig.compile_sdk

    defaultConfig {
        applicationId = GradleConfig.application_id
        minSdk = GradleConfig.min_sdk
        targetSdk = GradleConfig.target_sdk
        versionCode = 1
        versionName = GradleConfig.version_name
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
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
    //Projects
    implementation(project(ProjectDependencies.FeatureModule.core))
    implementation(project(ProjectDependencies.FeatureModule.bottombar))
    implementation(project(ProjectDependencies.FeatureModule.data))
    implementation(project(ProjectDependencies.FeatureModule.domain))

    //Dagger-hilt
    implementation(ProjectDependencies.Google.Dagger.dagger)
    kapt(ProjectDependencies.Google.Dagger.dagger_compiler)
}