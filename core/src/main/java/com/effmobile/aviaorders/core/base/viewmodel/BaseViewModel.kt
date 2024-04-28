package com.effmobile.aviaorders.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: Any, Action, Event>(initState: State? = null): ViewModel() {

    private val _viewStates = MutableStateFlow(initState)
    val viewStates = _viewStates.asSharedFlow()

    private val _viewActions = MutableSharedFlow<Action>()
    val viewActions = _viewActions.asSharedFlow()

    private val _viewEvents = MutableSharedFlow<Event>()
    val viewEvents = _viewEvents.asSharedFlow()

    protected fun sendViewAction(action: Action) {
        viewModelScope.launch {
            _viewActions.emit(action)
        }
    }

    protected fun updateViewState(state: State) {
        viewModelScope.launch {
            _viewStates.emit(state)
        }
    }

    abstract fun obtainEvent(viewEvent: Event)
}