package com.example.mvvmsimple

import android.widget.Toast
import com.example.mvvmsimple.CounterModel

class CounterRepository {

    private var counter= CounterModel(0)

    fun incrementCount(count: Int){
        counter = counter.copy(count = counter.count + count)
    }

    fun decrementCount(count: Int){
        if (counter.count <= 0 || counter.count < count){
            return
        }
        counter = counter.copy(count = counter.count - count)
    }

    fun resetCount(){
        counter = counter.copy(count = 0)
    }

    fun getCount(): Int{
        return counter.count
    }
}