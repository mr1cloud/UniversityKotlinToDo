package com.example.universitykotlintodo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.universitykotlintodo.domain.usecase.GetTodosUseCase
import com.example.universitykotlintodo.domain.usecase.ToggleTodoUseCase

class ViewModelFactory(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(
                getTodosUseCase,
                toggleTodoUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}