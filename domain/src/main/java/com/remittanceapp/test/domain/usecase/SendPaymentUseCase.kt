package com.remittanceapp.test.domain.usecase

import kotlinx.coroutines.delay

sealed class PaymentResult {
    object Success : PaymentResult()
    data class Error(val message: String) : PaymentResult()
}

class SendPaymentUseCase {
    suspend fun execute(amount: Double, recipientAddress: String): PaymentResult {
        // Simulate network delay and processing
        delay(2000)

        // Mock logic: for now, always return success.
        // In the future, this will interact with repositories.
        if (amount <= 0) {
            return PaymentResult.Error("Amount must be positive.")
        }
        if (recipientAddress.isBlank()) {
            return PaymentResult.Error("Recipient address is required.")
        }
        
        // Simulate a successful transaction
        return PaymentResult.Success
    }
}
