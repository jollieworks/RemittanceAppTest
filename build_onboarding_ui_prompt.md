# Prompt voor AI: Bouw de Onboarding UI

**Doel:** Pas de UI van de `OnboardingScreen` aan en verbind de navigatie-actie in de `NavGraph`. Dit is een standaard Jetpack Compose UI-taak.

**Context:**
Het project is een Android-app genaamd "RemittanceAppTest". Het is zojuist volledig hersteld en geconfigureerd naar een 100% stabiele en succesvol bouwende staat. Alle problemen met Gradle-versies, Kotlin-compatibiliteit en corrupte caches zijn opgelost. De huidige configuratie is geverifieerd en werkt. Het is **cruciaal** dat je onder geen beding wijzigingen aanbrengt in de build-bestanden (`.toml`, `.kts` in de root of `gradle` map). De taak is puur het aanpassen van twee Kotlin-bestanden.

---

### **Instructies:**

Je moet twee bestanden aanpassen.

#### **Bestand 1: `NavGraph.kt`**
-   **Doel:** Geef een navigatie-actie door aan het `OnboardingScreen`.
-   **Volledig pad:** `C:/Users/Gebruiker/AndroidStudioProjects/RemittanceAppTest/app/src/main/java/com/example/remittanceapptest/NavGraph.kt`
-   **Huidige inhoud:**
    ```kotlin
    package com.example.remittanceapptest
    
    import androidx.compose.runtime.Composable
    import com.remittanceapp.test.feature.onboarding.OnboardingScreen
    
    @Composable
    fun AppNavGraph() {
        OnboardingScreen()
    }
    ```
-   **Nieuwe, exacte inhoud:**
    ```kotlin
    package com.example.remittanceapptest
    
    import androidx.compose.runtime.Composable
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.remittanceapp.test.feature.onboarding.OnboardingScreen
    
    @Composable
    fun AppNavGraph() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "onboarding") {
            composable("onboarding") {
                OnboardingScreen(onKycComplete = { navController.navigate("home") })
            }
            composable("home") { /* Hier komt later het HomeScreen */ }
        }
    }
    ```

---

#### **Bestand 2: `OnboardingScreen.kt`**
-   **Doel:** Accepteer de navigatie-actie en bouw de UI met een `TextField` en een `Button`.
-   **Volledig pad:** `C:/Users/Gebruiker/AndroidStudioProjects/RemittanceAppTest/feature-onboarding/src/main/java/com/remittanceapp/test/feature/onboarding/OnboardingScreen.kt`
-   **Huidige inhoud:**
    ```kotlin
    package com.remittanceapp.test.feature.onboarding

    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable

    @Composable
    fun OnboardingScreen() {
        Text("Onboarding Screen")
    }
    ```
-   **Nieuwe, exacte inhoud:**
    ```kotlin
    package com.remittanceapp.test.feature.onboarding
    
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Button
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextField
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    
    @Composable
    fun OnboardingScreen(onKycComplete: () -> Unit) {
        var text by remember { mutableStateOf("") }
    
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = text,
                onValue-Change = { text = it },
                label = { Text("Enter your name") }
            )
            Button(onClick = onKycComplete) {
                Text("KYC voltooid")
            }
        }
    }
    ```

---

### **Verificatiestap:**

Nadat je deze twee bestanden exact hebt aangepast, voer je het volgende Gradle-commando uit om te verifiëren dat het project succesvol bouwt:
`./gradlew build`

Het verwachte resultaat is "BUILD SUCCESSFUL".
