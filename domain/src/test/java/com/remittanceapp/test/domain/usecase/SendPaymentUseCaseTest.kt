package com.remittanceapp.test.domain.usecase

import com.remittanceapp.test.domain.repository.TransactionRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

// A simple mock implementation for the test
class MockTransactionRepository : TransactionRepository {
    var shouldReturnSuccess = true
    override suspend fun uploadAmount(amount: Double): PaymentResult {
        return if (shouldReturnSuccess) {
            PaymentResult.Success
        } else {
            PaymentResult.Error("Mock error")
        }
    }
}

class SendPaymentUseCaseTest {

    private lateinit var sendPaymentUseCase: SendPaymentUseCase
    private lateinit var mockRepository: MockTransactionRepository

    @Before
    fun setUp() {
        mockRepository = MockTransactionRepository()
        sendPaymentUseCase = SendPaymentUseCase(mockRepository)
    }

    @Test
    fun `execute payment with valid data returns success`() = runTest {
        // Arrange
        val amount = 100.0
        mockRepository.shouldReturnSuccess = true

        // Act
        val result = sendPaymentUseCase.execute(amount)

        // Assert
        assertTrue(result is PaymentResult.Success)
    }

    @Test
    fun `execute payment with zero amount returns error`() = runTest {
        // Arrange
        val amount = 0.0

        // Act
        val result = sendPaymentUseCase.execute(amount)

        // Assert
        assertTrue(result is PaymentResult.Error)
    }
    
    @Test
    fun `repository failure returns error`() = runTest {
        // Arrange
        val amount = 100.0
        mockRepository.shouldReturnSuccess = false

        // Act
        val result = sendPaymentUseCase.execute(amount)

        // Assert
        assertTrue(result is PaymentResult.Error)
    }
}
