import ProjectDependencies.Plugins.Dagger.dagger_hilt_version

object ProjectDependencies {

    object Plugins {
        object Android {
            const val gradle_version = "8.1.0"
            const val android_application = "com.android.application"
            const val android_library = "com.android.library"
        }
        object JetBrains {
            const val kotlin_version = "1.9.22"
            const val kotlin_android = "android"
            const val kotlin_kapt = "kapt"
            const val kotlin_jvm = "jvm"
        }

        object Dagger {
            const val dagger_hilt_version = "2.47"
            const val dagger_hilt = "com.google.dagger.hilt.android"
            const val dagger_hilt_plugin = "dagger.hilt.android.plugin"
            const val inject = "javax.inject:javax.inject:1"
        }
    }

    object FeatureModule {
        const val PATH = "path"
        const val app = ":app"
        const val core = ":core"
        const val resources = ":resources"
        const val bottombar = ":features:bottombar"
        const val domain = ":domain"
        const val data =":data"
    }

    object Glide {
        const val version = "4.15.1"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glide_compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Google {
        const val material = "com.google.android.material:material:1.9.0"
        const val gson = "com.google.code.gson:gson:2.10.1"

        object Dagger {
            const val dagger = "com.google.dagger:hilt-android:$dagger_hilt_version"
            const val dagger_compiler = "com.google.dagger:hilt-android-compiler:$dagger_hilt_version"
        }
    }

    object AndroidX {
        object Lifecycle {
            private const val version = "2.6.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val process = "androidx.lifecycle:lifecycle-process:$version"
        }
        object Navigation {
            private const val version = "2.6.0"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val features = "androidx.navigation:navigation-dynamic-features-fragment:$version"
            const val runtime = "androidx.navigation:navigation-runtime-ktx:$version"
        }
        object Core {
            const val core = "androidx.core:core-ktx:1.10.1"
            const val activity = "androidx.activity:activity:1.8.2"
            const val activity_ktx = "androidx.activity:activity-ktx:1.8.2"
            const val appcompat = "androidx.appcompat:appcompat:1.6.1"
            const val preference = "androidx.preference:preference-ktx:1.2.0"
            const val recyclerview = "androidx.recyclerview:recyclerview:1.3.1"
            const val recyclerview_selection = "androidx.recyclerview:recyclerview-selection:1.1.0"
            const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
            const val xxpermission = "com.github.getActivity:XXPermissions:16.2"
            const val swipeToRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
            const val dynamicanimation = "androidx.dynamicanimation:dynamicanimation-ktx:1.0.0-alpha03"
            const val percent_layout = "androidx.percentlayout:percentlayout:1.0.0"
            const val palette = "androidx.palette:palette:1.0.0"
        }
        val androidx_list = listOf(
            Lifecycle.runtime, Lifecycle.viewmodel, Lifecycle.process,
            Navigation.ui, Navigation.fragment, Navigation.features, Navigation.runtime,
            Core.core, Core.appcompat, Core.preference, Core.recyclerview, Core.recyclerview_selection,
            Core.constraint, Core.swipeToRefresh, Core.percent_layout, Core.dynamicanimation,Core.palette,
            Core.activity, Core.activity_ktx
        )
    }

    object Retrofit {
        const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofit_converter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHTTP {
        const val version = "4.9.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Coroutines {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
    }

    object Room {
        private const val version = "2.5.2"
        const val room = "androidx.room:room-ktx:$version"
        const val room_compiler = "androidx.room:room-compiler:$version"
    }
}