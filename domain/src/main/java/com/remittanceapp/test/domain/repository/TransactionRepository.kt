package com.remittanceapp.test.domain.repository

import com.remittanceapp.test.domain.usecase.PaymentResult

interface TransactionRepository {
    suspend fun uploadAmount(amount: Double): PaymentResult
}
