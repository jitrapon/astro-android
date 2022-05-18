plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.jitrapon.astro"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    namespace = "io.jitrapon.astro"
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))

    // Jetpack Compose
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.material:material:1.2.0-beta01")
    implementation("androidx.compose.animation:animation:1.2.0-beta01")
    implementation("androidx.compose.ui:ui-tooling:1.2.0-beta01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.compose.ui:ui:1.2.0-beta01")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-beta01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-beta01")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-beta01")
}