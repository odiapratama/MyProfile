plugins {
    alias(libs.plugins.ksp)
    id("androidx.room")
}

android {
    namespace = "com.problemsolver.core.data"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    api(libs.bundles.retrofit)
    api(libs.okhttp.logging)
    api(libs.bundles.kotlinx)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    api(libs.bundles.androidx.lifecyle)
    api(libs.bundles.room)
    ksp(libs.room.compiler)
    api(libs.bundles.paging)
}
