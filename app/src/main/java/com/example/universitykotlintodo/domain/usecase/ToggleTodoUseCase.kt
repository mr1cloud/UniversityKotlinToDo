package com.example.universitykotlintodo.domain.usecase

import com.example.universitykotlintodo.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) {
        return repository.toggleTodo(id)
    }
}