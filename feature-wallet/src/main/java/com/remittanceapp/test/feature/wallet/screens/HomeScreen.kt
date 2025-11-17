package com.remittanceapp.test.feature.wallet.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remittanceapp.test.feature.wallet.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onUploadClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val balanceState by viewModel.balanceState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Saldo: ${balanceState.euroBalance} / ${balanceState.srdBalance}")
        Button(onClick = onUploadClick) {
            Text("Upload")
        }
    }
}
