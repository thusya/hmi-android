package com.thusee.hmi.data.local

import android.content.Context
import android.content.SharedPreferences
import com.thusee.hmi.data.repository.CounterRepository
import com.thusee.hmi.domain.model.Counter
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CounterRepositoryImplTest {
    private lateinit var counterRepository: CounterRepository
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @BeforeEach
    fun setUp() {
        sharedPreferences = mockk(relaxed = true)
        sharedPreferencesEditor = mockk(relaxed = true)

        every { sharedPreferences.edit() } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.putInt(any(), any()) } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.apply() } just Runs

        val context = mockk<Context>(relaxed = true)
        every {
            context.getSharedPreferences(
                "counter_pref",
                Context.MODE_PRIVATE
            )
        } returns sharedPreferences

        counterRepository = CounterRepositoryImpl(context)
    }

    @Test
    fun `storeValue stores the value correctly`() = runTest {
        val value = 15

        counterRepository.storeValue(value)

        verify(exactly = 1) { sharedPreferencesEditor.putInt("counter_value", value) }
        verify(exactly = 1) { sharedPreferencesEditor.apply() }
    }

    @Test
    fun `getValue returns the correct value`() = runTest {
        val defaultValue = 10
        val storedValue = 20

        every { sharedPreferences.getInt("counter_value", defaultValue) } returns storedValue

        val result = counterRepository.getValue()

        assertEquals(Counter(storedValue), result)
    }
}