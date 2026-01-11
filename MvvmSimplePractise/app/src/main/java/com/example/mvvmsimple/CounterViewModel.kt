package com.example.mvvmsimple

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    private val repository = CounterRepository()
    var count by mutableStateOf(repository.getCount())
        private set

    fun incrementCount(countToChange: Int=1){
        repository.incrementCount(countToChange)
        count = repository.getCount()
    }

    fun decrementCount(countToChange: Int=1){
        repository.decrementCount(countToChange)
        count = repository.getCount()
    }

    fun resetCount() {
        repository.resetCount()
        count = repository.getCount()
    }
}