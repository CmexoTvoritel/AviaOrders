plugins {
    id(ProjectDependencies.Plugins.Android.android_library)
    id(ProjectDependencies.Plugins.Dagger.dagger_hilt)
    kotlin(ProjectDependencies.Plugins.JetBrains.kotlin_android)
    kotlin(ProjectDependencies.Plugins.JetBrains.kotlin_kapt)
}

android {
    namespace = "${GradleConfig.namespace}.core"
    compileSdk = GradleConfig.compile_sdk

    defaultConfig {
        minSdk = GradleConfig.min_sdk
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
    api(project(ProjectDependencies.FeatureModule.resources))

    //AndroidX
    ProjectDependencies.AndroidX.androidx_list.forEach { androidxDep ->
        api(androidxDep)
    }

    //Room
    implementation(ProjectDependencies.Room.room)
    kapt(ProjectDependencies.Room.room_compiler)

    //Dagger-hilt
    implementation(ProjectDependencies.Google.Dagger.dagger)
    kapt(ProjectDependencies.Google.Dagger.dagger_compiler)

    //Glide
    api(ProjectDependencies.Glide.glide)
    kapt(ProjectDependencies.Glide.glide_compiler)

    //Retrofit
    api(ProjectDependencies.Retrofit.retrofit)
    api(ProjectDependencies.Retrofit.retrofit_converter)

    //OkHttp
    api(ProjectDependencies.OkHTTP.okhttp)
    api(ProjectDependencies.OkHTTP.okhttp_interceptor)

    //Json
    api(ProjectDependencies.Google.gson)

}