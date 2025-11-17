package com.remittanceapp.test.domain.usecase

import com.remittanceapp.test.domain.repository.TransactionRepository
import javax.inject.Inject

sealed class PaymentResult {
    object Success : PaymentResult()
    data class Error(val message: String) : PaymentResult()
}

class SendPaymentUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun execute(amount: Double): PaymentResult {
        if (amount <= 0) {
            return PaymentResult.Error("Amount must be positive.")
        }
        return transactionRepository.uploadAmount(amount)
    }
}
