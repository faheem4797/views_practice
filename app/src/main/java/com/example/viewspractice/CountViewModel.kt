package com.example.viewspractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {
    val count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun increment(){
        count.value = (count.value)?.plus(1)
    }
}