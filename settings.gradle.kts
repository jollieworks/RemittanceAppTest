pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "RemittanceAppTest"
include(":app")
include(":feature-onboarding")
include(":feature-transactions")
include(":feature-wallet")
include(":feature-receiver")
include(":core")
include(":data")
include(":domain")
include(":network")
