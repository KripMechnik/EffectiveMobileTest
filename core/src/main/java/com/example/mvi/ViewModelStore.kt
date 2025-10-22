package com.example.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ViewModelStore<State : Any, SideEffect>(initialState: State): ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<SideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    init {
        _state.onEach { state ->
            sideEffectProducer(state)
        }.launchIn(viewModelScope)
    }

    private fun internalReduce(block: State.() -> State) {
        _state.update {
            _state.value.let(block)
        }
    }

    fun intent(block: suspend IntentScope.() -> Unit) {
        viewModelScope.launch {
            block(scope)
        }
    }

    private val scope = IntentScope()

    inner class IntentScope {
        fun reduce(block: State.() -> State) {
            internalReduce {
                block()
            }
        }
    }

    abstract fun sideEffectProducer(state: State): SideEffect
}