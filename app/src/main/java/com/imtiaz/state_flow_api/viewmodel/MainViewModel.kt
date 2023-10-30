package com.imtiaz.state_flow_api.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    val _counter: MutableStateFlow<Int> = MutableStateFlow(0)
    val counter:StateFlow<Int> = _counter

    fun increment(){
        _counter.value++
    }

    fun decrement(){
        _counter.value--
    }
}