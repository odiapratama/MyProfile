plugins {
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.problemsolver.feature.profile"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    api(projects.core.data)
    api(projects.core.utils)
    api(projects.core.ui)
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)
}