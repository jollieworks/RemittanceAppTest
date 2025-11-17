package com.remittanceapp.test.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val amount: Double,
    val currency: String = "EUR"
)

@Serializable
data class PaymentResponse(
    val transactionId: String,
    val status: String
)
