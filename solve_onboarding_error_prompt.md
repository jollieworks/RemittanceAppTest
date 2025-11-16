# Prompt voor AI: Los "Unresolved reference" Fout op in Android Project

**Doel:** Los een hardnekkige `Unresolved reference: OnboardingScreen` build-fout op in een Android-project en zorg dat het project weer succesvol bouwt.

---

### **Context & Probleemomschrijving**

Het project was volledig stabiel en kon succesvol builden. De problemen begonnen na het toevoegen van een nieuwe module (`:feature-wallet`) en het bijbehorende `HomeScreen`. Sindsdien kan de compiler de `OnboardingScreen` composable niet meer vinden, ook al lijkt de import-verklaring in `NavGraph.kt` correct.

**De cruciale aanwijzing is dat een poging om het `OnboardingScreen.kt` bestand te lezen, mislukte met `Error: file not found`. Dit suggereert dat het bestand per ongeluk is verwijderd of verplaatst.**

De taak is om de oorzaak van dit probleem te vinden, het te herstellen, en de build weer werkend te krijgen.

---

### **Exacte Foutmelding**

```
e: file:///C:/Users/Gebruiker/AndroidStudioProjects/RemittanceAppTest/app/src/main/java/com/example/remittanceapptest/NavGraph.kt:7:50 Unresolved reference 'OnboardingScreen'.
e: file:///C:/Users/Gebruiker/AndroidStudioProjects/RemittanceAppTest/app/src/main/java/com/example/remittanceapptest/NavGraph.kt:15:13 Unresolved reference 'OnboardingScreen'.
```

---

### **Staat van Relevante Bestanden**

Hieronder volgt de exacte inhoud van alle relevante bestanden.

#### **1. `NavGraph.kt` (Bevat de fout)**
*Pad: `app/src/main/java/com/example/remittanceapptest/NavGraph.kt`*
```kotlin
package com.example.remittanceapptest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remittanceapp.test.feature.onboarding.OnboardingScreen // This import fails
import com.remittanceapp.test.feature.wallet.screens.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen(onKycComplete = { navController.navigate("home") })
        }
        composable("home") {
            HomeScreen(onUploadClick = { navController.navigate("upload") })
        }
    }
}
```

#### **2. `gradle/libs.versions.toml` (Versiebeheer)**
```toml
[versions]
agp = "8.13.1"
kotlin = "2.2.10"
composeCompiler = "1.5.14"
coreKtx = "1.17.0"
androidxJunit = "1.3.0"
espressoCore = "3.7.0"
lifecycleRuntimeKtx = "2.9.2"
activityCompose = "1.10.1"
composeBom = "2025.08.00"
# ... (andere versies) ...

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
# ... (andere plugins) ...
```

#### **3. `app/build.gradle.kts` (App Module Configuratie)**
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // ...
}

dependencies {
    implementation(project(":feature-onboarding"))
    implementation(project(":feature-wallet"))
    // ...
}
```

#### **4. `feature-onboarding/build.gradle.kts` (Onboarding Module Configuratie)**
```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.remittanceapp.test.feature.onboarding"
    // ...
}
// LET OP: DEZE DEPENDENCIES ZIJN MINIMAAL
dependencies {
    // Dependencies will be added in a later phase
}
```

---

### **Voorgesteld Stappenplan voor Oplossing**

1.  **Vind het Bestand:** Bevestig dat het bestand `OnboardingScreen.kt` inderdaad ontbreekt op het pad `C:/Users/Gebruiker/AndroidStudioProjects/RemittanceAppTest/feature-onboarding/src/main/java/com/remittanceapp/test/feature/onboarding/OnboardingScreen.kt`. Gebruik de zoekfunctionaliteit van de IDE indien nodig om het te vinden.
2.  **Herstel het Bestand:** Als het bestand ontbreekt, maak het dan opnieuw aan op het juiste pad met de correcte inhoud. De inhoud zou vergelijkbaar moeten zijn met de UI-code die we eerder probeerden te implementeren.
    ```kotlin
    package com.remittanceapp.test.feature.onboarding

    // Voeg hier alle benodigde Compose imports toe
    import androidx.compose.runtime.Composable
    import androidx.compose.material3.Text
    // ... etc.

    @Composable
    fun OnboardingScreen(onKycComplete: () -> Unit) {
        // Implementeer hier de UI met een TextField en een Button
        Text("TODO: Implement OnboardingScreen UI")
    }
    ```
3.  **Controleer Dependencies:** Zorg ervoor dat het `feature-onboarding/build.gradle.kts` bestand alle benodigde Compose-dependencies bevat (`compose.ui`, `compose.material3`, `compose.runtime`, etc.), anders zal de UI-code niet compileren.
4.  **Verifieer:** Voer een `./gradlew build` uit om te bevestigen dat de `Unresolved reference` fout is opgelost en het project succesvol compileert.
