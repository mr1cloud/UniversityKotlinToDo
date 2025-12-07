package com.example.universitykotlintodo

import com.example.universitykotlintodo.domain.model.TodoItem
import com.example.universitykotlintodo.domain.repository.TodoRepository
import com.example.universitykotlintodo.domain.usecase.GetTodosUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTodosUseCaseTest {

    private lateinit var repository: TodoRepository
    private lateinit var useCase: GetTodosUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetTodosUseCase(repository)
    }

    @Test
    fun `GetTodosUseCase returns 3 tasks`() = runTest {
        val expectedTodos = listOf(
            TodoItem(id = 1, title = "Task 1", description = "Description 1", isCompleted = false),
            TodoItem(id = 2, title = "Task 2", description = "Description 2", isCompleted = true),
            TodoItem(id = 3, title = "Task 3", description = "Description 3", isCompleted = false)
        )
        coEvery { repository.getTodos() } returns expectedTodos

        val result = useCase()

        assertEquals(3, result.size)
        assertEquals(expectedTodos, result)
    }
}

