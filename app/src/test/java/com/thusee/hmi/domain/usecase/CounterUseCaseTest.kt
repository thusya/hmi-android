package com.thusee.hmi.domain.usecase

import com.thusee.hmi.data.repository.CounterRepository
import com.thusee.hmi.domain.model.Counter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CounterUseCaseTest {
    private lateinit var counterUseCase: CounterUseCase
    private val repository: CounterRepository = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        counterUseCase = CounterUseCase(repository)
    }

    @Test
    fun `getCurrentValue returns the correct value`() = runTest {
        val expectedCounter = Counter(15)
        coEvery { repository.getValue() } returns expectedCounter

        val result = counterUseCase.getCurrentValue()

        assertEquals(expectedCounter, result)
    }

    @Test
    fun `counterIncrement increments value correctly when below max value`() = runTest {
        val currentCounter = Counter(28)
        coEvery { repository.getValue() } returns currentCounter

        counterUseCase.counterIncrement()

        coVerify(exactly = 1) { repository.storeValue(30) }
    }

    @Test
    fun `counterIncrement does not increment value when at max value`() = runTest {
        val currentCounter = Counter(30)
        coEvery { repository.getValue() } returns currentCounter

        counterUseCase.counterIncrement()

        coVerify(exactly = 0) { repository.storeValue(any()) }
    }

    @Test
    fun `counterDecrement decrements value correctly when above min value`() = runTest {
        val currentCounter = Counter(12)
        coEvery { repository.getValue() } returns currentCounter

        counterUseCase.counterDecrement()

        coVerify(exactly = 1) { repository.storeValue(10) }
    }

    @Test
    fun `counterDecrement does not decrement value when at min value`() = runTest {
        val currentCounter = Counter(10)
        coEvery { repository.getValue() } returns currentCounter

        counterUseCase.counterDecrement()

        coVerify(exactly = 0) { repository.storeValue(any()) }
    }
}