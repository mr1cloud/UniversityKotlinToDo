package com.example.universitykotlintodo.domain.repository

import com.example.universitykotlintodo.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}