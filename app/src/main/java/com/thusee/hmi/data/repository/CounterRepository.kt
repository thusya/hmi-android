package com.thusee.hmi.data.repository

import com.thusee.hmi.domain.model.Counter

interface CounterRepository {
    suspend fun getValue(): Counter
    suspend fun storeValue(value: Int)
}