package com.example.universitykotlintodo.domain.usecase

import com.example.universitykotlintodo.domain.model.TodoItem
import com.example.universitykotlintodo.domain.repository.TodoRepository

class GetTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<TodoItem> = repository.getTodos()
}