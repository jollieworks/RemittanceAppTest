package com.remittanceapp.test.feature.upload.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remittanceapp.test.feature.upload.viewmodel.UploadUiState
import com.remittanceapp.test.feature.upload.viewmodel.UploadViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UploadScreen(
    onPaymentComplete: () -> Unit,
    viewModel: UploadViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiState.collectLatest { state ->
            if (state is UploadUiState.Success) {
                onPaymentComplete()
            }
        }
    }

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
            label = { Text("Bedrag") },
            isError = uiState is UploadUiState.Error
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is UploadUiState.Loading -> {
                CircularProgressIndicator()
            }
            is UploadUiState.Error -> {
                Text((uiState as UploadUiState.Error).message)
            }
            else -> {
                Button(onClick = { viewModel.processPayment(amount) }) {
                    Text("IDEAL (Mock) Betalen")
                }
            }
        }
    }
}
