package com.remittanceapp.test.feature.upload.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remittanceapp.test.domain.usecase.PaymentResult
import com.remittanceapp.test.domain.usecase.SendPaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UploadUiState {
    object Idle : UploadUiState()
    object Loading : UploadUiState()
    object Success : UploadUiState()
    data class Error(val message: String) : UploadUiState()
}

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val sendPaymentUseCase: SendPaymentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UploadUiState>(UploadUiState.Idle)
    val uiState: StateFlow<UploadUiState> = _uiState

    fun processPayment(amountStr: String) {
        val amount = amountStr.toDoubleOrNull()
        if (amount == null) {
            _uiState.value = UploadUiState.Error("Invalid amount")
            return
        }

        viewModelScope.launch {
            _uiState.value = UploadUiState.Loading
            when (sendPaymentUseCase.execute(amount)) {
                is PaymentResult.Success -> _uiState.value = UploadUiState.Success
                is PaymentResult.Error -> _uiState.value = UploadUiState.Error("Payment Failed")
            }
        }
    }
}
