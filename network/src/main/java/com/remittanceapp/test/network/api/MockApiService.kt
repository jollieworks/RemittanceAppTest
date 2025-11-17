package com.remittanceapp.test.network.api

import com.remittanceapp.test.network.dto.PaymentRequest
import com.remittanceapp.test.network.dto.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MockApiService {
    @POST("upload")
    suspend fun processPayment(@Body request: PaymentRequest): PaymentResponse
}
