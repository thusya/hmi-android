package com.thusee.hmi.data.local

import android.content.Context
import android.content.SharedPreferences
import com.thusee.hmi.data.repository.CounterRepository
import com.thusee.hmi.domain.model.Counter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : CounterRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("counter_pref", Context.MODE_PRIVATE)

    companion object {
        const val KEY_COUNTER_VALUE = "counter_value"
    }

    override suspend fun storeValue(value: Int) {
        sharedPreferences.edit().putInt(KEY_COUNTER_VALUE, value).apply()
    }

    override suspend fun getValue(): Counter {
        val storedValue = sharedPreferences.getInt(KEY_COUNTER_VALUE, 10)
        return Counter(storedValue)
    }
}