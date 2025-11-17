package com.remittanceapp.test.data.repository

import com.remittanceapp.test.domain.repository.TransactionRepository
import com.remittanceapp.test.domain.usecase.PaymentResult
import com.remittanceapp.test.network.api.MockApiService
import com.remittanceapp.test.network.dto.PaymentRequest
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val apiService: MockApiService
) : TransactionRepository {

    override suspend fun uploadAmount(amount: Double): PaymentResult {
        return try {
            val request = PaymentRequest(amount = amount)
            val response = apiService.processPayment(request)
            if (response.status == "SUCCESS") {
                PaymentResult.Success
            } else {
                PaymentResult.Error("Payment failed with status: ${response.status}")
            }
        } catch (e: Exception) {
            PaymentResult.Error(e.message ?: "An unknown error occurred")
        }
    }
}
