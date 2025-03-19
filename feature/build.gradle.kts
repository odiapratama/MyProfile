import com.android.build.gradle.BaseExtension

plugins {
    id("java-platform")
}

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")

    plugins.withType(BasePlugin::class.java).configureEach {
        configure<BaseExtension> {
            defaultConfig {
                minSdk = 24
                compileSdkVersion(35)
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
//                isCoreLibraryDesugaringEnabled = true
            }
        }
    }
}

dependencies {
    constraints {
        api(projects.feature.profile)
    }
}