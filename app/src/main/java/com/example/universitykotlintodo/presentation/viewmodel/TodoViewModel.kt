package com.example.universitykotlintodo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitykotlintodo.domain.model.TodoItem
import com.example.universitykotlintodo.domain.usecase.GetTodosUseCase
import com.example.universitykotlintodo.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TodoState(isLoading = true))
    val state: StateFlow<TodoState> = _state

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            val todos = getTodosUseCase()
            _state.update { current ->
                current.copy(isLoading = false, todos = todos)
            }
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            loadTodos()
        }
    }

    fun setDetailedView(todoItem: TodoItem?) {
        _state.update { current ->
            current.copy(detailedView = todoItem)
        }
    }
}