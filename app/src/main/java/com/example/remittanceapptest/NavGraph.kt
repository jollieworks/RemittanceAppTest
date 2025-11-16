package com.example.remittanceapptest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remittanceapp.test.feature.onboarding.OnboardingScreen
import com.remittanceapp.test.feature.receiver.screens.ReceiveScreen
import com.remittanceapp.test.feature.transactions.screens.SendScreen
import com.remittanceapp.test.feature.upload.screens.UploadScreen
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
        composable("upload") {
            UploadScreen(onPaymentComplete = { navController.navigate("send") })
        }
        composable("send") {
            SendScreen(onSendClick = { navController.navigate("receive") })
        }
        composable("receive") {
            ReceiveScreen(onComplete = { /* Flow is done */ })
        }
    }
}
