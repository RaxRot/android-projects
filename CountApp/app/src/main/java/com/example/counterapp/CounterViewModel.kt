package com.example.counterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val repository = CounterRepository()

    var count by mutableStateOf(repository.getCount())
        private set

    fun incrementCount() {
        repository.incrementCount()
        count = repository.getCount()
    }

    fun decrementCount() {
        repository.decrementCount()
        count = repository.getCount()
    }
}
