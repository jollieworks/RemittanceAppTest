package com.remittanceapp.test.feature.wallet.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class BalanceState(
    val euroBalance: String = "€1,234.56",
    val srdBalance: String = "SRD 45,678.90"
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _balanceState = MutableStateFlow(BalanceState())
    val balanceState: StateFlow<BalanceState> = _balanceState

    // In a real app, this would be populated by a UseCase that calls a Repository.
    // For now, we just use the mock data from the initial state.
}
