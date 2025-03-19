pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyProfile"
include(":app")
include(":core")
include(":core:data")
include(":core:utils")
include(":feature")
include(":feature:profile")
include(":core:ui")
