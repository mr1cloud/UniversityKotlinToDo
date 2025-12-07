package com.example.universitykotlintodo.presentation.viewmodel

import com.example.universitykotlintodo.domain.model.TodoItem

data class TodoState(
    val isLoading: Boolean = false,
    val todos: List<TodoItem> = emptyList(),
    var detailedView: TodoItem? = null
)