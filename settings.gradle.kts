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
    }
}

rootProject.name = "RemittanceAppTest"
include(":app")
include(":feature-onboarding")
include(":feature-transactions")
include(":feature-wallet")
include(":feature-receiver")
include(":feature-upload")
include(":core")
include(":data")
include(":domain")
include(":network")
