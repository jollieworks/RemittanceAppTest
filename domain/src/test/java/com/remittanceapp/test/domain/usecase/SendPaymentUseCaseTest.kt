package com.remittanceapp.test.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SendPaymentUseCaseTest {

    private lateinit var sendPaymentUseCase: SendPaymentUseCase

    @Before
    fun setUp() {
        sendPaymentUseCase = SendPaymentUseCase()
    }

    @Test
    fun `execute payment with valid data returns success`() = runTest {
        // Arrange
        val amount = 100.0
        val recipient = "test_recipient"

        // Act
        val result = sendPaymentUseCase.execute(amount, recipient)

        // Assert
        assertTrue(result is PaymentResult.Success)
    }

    @Test
    fun `execute payment with zero amount returns error`() = runTest {
        // Arrange
        val amount = 0.0
        val recipient = "test_recipient"

        // Act
        val result = sendPaymentUseCase.execute(amount, recipient)

        // Assert
        assertTrue(result is PaymentResult.Error)
    }

    @Test
    fun `execute payment with blank recipient returns error`() = runTest {
        // Arrange
        val amount = 100.0
        val recipient = ""

        // Act
        val result = sendPaymentUseCase.execute(amount, recipient)

        // Assert
        assertTrue(result is PaymentResult.Error)
    }
}
