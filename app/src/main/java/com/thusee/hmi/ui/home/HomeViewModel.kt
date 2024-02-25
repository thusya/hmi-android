package com.thusee.hmi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.hmi.domain.model.Counter
import com.thusee.hmi.domain.usecase.CounterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val counterUseCase: CounterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(Counter())
    val uiState: StateFlow<Counter> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = counterUseCase.getCurrentValue()
        }
    }

    fun increment() {
        viewModelScope.launch { counterUseCase.counterIncrement() }
    }

    fun decrement() {
        viewModelScope.launch { counterUseCase.counterDecrement() }
    }

}