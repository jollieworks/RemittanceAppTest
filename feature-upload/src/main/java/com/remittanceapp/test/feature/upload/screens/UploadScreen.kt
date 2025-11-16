package com.remittanceapp.test.feature.upload.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch // Added this import

@Composable
fun UploadScreen(onPaymentComplete: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Bedrag") }
        )
        Button(onClick = {
            coroutineScope.launch {
                delay(1000) // Simulate payment processing
                onPaymentComplete()
            }
        }) {
            Text("IDEAL (Mock) Betalen")
        }
    }
}
