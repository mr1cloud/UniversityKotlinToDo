package com.example.universitykotlintodo.data.repository

import com.example.universitykotlintodo.data.local.TodoJsonDataSource
import com.example.universitykotlintodo.domain.model.TodoItem
import com.example.universitykotlintodo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val jsonSource: TodoJsonDataSource
) : TodoRepository {
    private var todosCache = mutableListOf<TodoItem>()

    override suspend fun getTodos(): List<TodoItem> {
        if (todosCache.isEmpty()) {
            todosCache = jsonSource.getTodos().map { dto ->
                TodoItem(
                    id = dto.id,
                    title = dto.title,
                    description = dto.description,
                    isCompleted = dto.isCompleted
                )
            }.toMutableList()
        }
        return todosCache.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = todosCache.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = todosCache[index]
            todosCache[index] = item.copy(isCompleted = !item.isCompleted)
        }
    }
}