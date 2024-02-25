package com.thusee.hmi.domain.usecase

import com.thusee.hmi.data.repository.CounterRepository
import com.thusee.hmi.domain.model.Counter
import javax.inject.Inject

class CounterUseCase @Inject constructor(private val repository: CounterRepository) {
    suspend fun getCurrentValue(): Counter = repository.getValue()

    suspend fun counterIncrement() {
        val currentValue = repository.getValue().value

        if (currentValue < 30) {
            repository.storeValue(currentValue + 2)
        }
    }

    suspend fun counterDecrement() {
        val currentValue = repository.getValue().value

        if (currentValue > 10) {
            repository.storeValue(currentValue - 2)
        }
    }
}