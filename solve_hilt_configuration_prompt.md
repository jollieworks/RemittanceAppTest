# Prompt voor Expert: Los Hardnekkig "Plugin Not Found" Probleem op voor Hilt/KSP

**Doel:** Configureer Hilt en KSP succesvol in dit modulaire Android-project.

---

### **Context & Probleemomschrijving**

We proberen Hilt en KSP te implementeren om te beginnen met Fase 3 (ViewModels). Het project is verder stabiel en bouwt succesvol zónder de Hilt/KSP configuratie. Echter, elke poging om de KSP-plugin toe te voegen, faalt consistent met de volgende fout, ongeacht de gekozen syntax of versie:

**Exacte Foutmelding:**
```
Plugin [id: 'com.google.devtools.ksp'] was not found in any of the following sources:
- Gradle Core Plugins
- Included Builds
- Plugin Repositories (could not resolve plugin artifact 'com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:...')
  Searched in the following repositories:
    Google
    MavenRepo
    Gradle Central Plugin Repository
```

Dit is onlogisch, omdat de `gradlePluginPortal()` expliciet is gedefinieerd in `settings.gradle.kts`, waar KSP wordt gehost.

---

### **Wat We Hebben Geprobeerd (zonder succes)**

We hebben verschillende strategieën gevolgd, gebaseerd op externe analyses, Stack Overflow en de officiële documentatie:

1.  **Aanpak 1: Via Version Catalog Aliassen**
    *   We hebben geprobeerd `hilt` en `ksp` plugins te definiëren in `libs.versions.toml` en deze aan te roepen met `alias(libs.plugins. ...)` in de `build.gradle.kts` bestanden. Dit faalde.

2.  **Aanpak 2: Directe Declaratie (Gebaseerd op Stack Overflow en Officiële Docs)**
    *   We hebben de officiële [KSP Quickstart Gids](https://kotlinlang.org/docs/ksp-quickstart.html) en een relevante [Stack Overflow Oplossing](https://stackoverflow.com/questions/79703961/gradle-sync-fails-plugin-id-com-google-devtools-ksp-was-not-found-on-a-c) gevolgd. Beide raden aan om de plugins direct in het root `build.gradle.kts` te declareren met `id("...") version "..."`.
    *   Ook deze aanpak resulteert in exact dezelfde "Plugin not found" fout.

3.  **Versie-experimenten:**
    *   We hebben de "exacte match" KSP-versie voor onze Kotlin-versie (`2.2.10-1.0.24`) geprobeerd.
    *   We hebben uit wanhoop een oudere, bekende stabiele KSP-versie (`1.9.23-1.0.13`) geprobeerd.
    *   Beide pogingen geven dezelfde fout.

De conclusie is dat er een dieper, onzichtbaar probleem is dat voorkomt dat Gradle de `gradlePluginPortal()` correct raadpleegt voor de root build script plugins.

---

### **Huidige (Niet-Werkende) Staat: Volledige Build-Configuratie**

Hieronder staat de **exacte configuratie** van de laatste, mislukte poging, die de officiële documentatie volgt.

#### **1. `gradle/libs.versions.toml`**
```toml
[versions]
agp = "8.12.1"
kotlin = "2.2.10"
composeCompiler = "1.5.14"
hilt = "2.52"
ksp = "2.2.10-1.0.24"
# ... andere versies ...

[libraries]
# ... andere libraries ...
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlinSerializationPlugin" }
# GEEN HILT/KSP HIER VOLGENS DE DIRECTE AANPAK
```

#### **2. Root `build.gradle.kts`**
```kotlin
plugins {
    id("com.android.application") version "8.12.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.10" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    id("com.google.devtools.ksp") version "2.2.10-1.0.24" apply false // Dit faalt
}
```

#### **3. `settings.gradle.kts`**
```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // KSP zou hier gevonden moeten worden
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
include(":app", ":feature-onboarding", /* ... alle andere modules ... */)
```

#### **4. `app/build.gradle.kts` (Doelconfiguratie)**
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // We zouden hier de Hilt/KSP plugins willen toevoegen
}
// ...
dependencies {
    // We zouden hier de Hilt dependencies willen toevoegen
    // implementation(libs.hilt.android)
    // ksp(libs.hilt.compiler)
}
```

De overige module `build.gradle.kts` bestanden zijn standaard en bevatten nog geen Hilt/KSP configuratie. Hun inhoud is beschikbaar indien nodig, maar de fout treedt al op in de root-configuratie.

---

### **De Vraag aan de Expert:**

Gezien de bovenstaande context, waarom faalt Gradle consistent in het vinden van de KSP-plugin in de `gradlePluginPortal()`, zelfs wanneer de officiële documentatie exact wordt gevolgd? Is er een verborgen configuratie, een mogelijke bug, of een omgevingsprobleem dat we over het hoofd zien? Wat is de definitieve, waterdichte manier om Hilt en KSP in dit project te configureren?
