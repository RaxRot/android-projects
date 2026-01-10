package com.example.counterapp

class CounterRepository {
    private var counter = CounterModel(0)

    fun incrementCount(){
        counter = counter.copy(count = counter.count + 1)
    }

    fun decrementCount(){
        counter = counter.copy(count = counter.count - 1)
    }

    fun getCount(): Int{
        return counter.count
    }
}