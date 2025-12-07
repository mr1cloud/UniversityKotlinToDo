package com.example.universitykotlintodo

import com.example.universitykotlintodo.domain.repository.TodoRepository
import com.example.universitykotlintodo.domain.usecase.ToggleTodoUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleTodoUseCaseTest {

    private lateinit var repository: TodoRepository
    private lateinit var useCase: ToggleTodoUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = ToggleTodoUseCase(repository)
    }

    @Test
    fun `toggleTodo changes isCompleted`() = runTest {
        val todoId = 1
        coEvery { repository.toggleTodo(todoId) } just runs

        useCase(todoId)

        coVerify(exactly = 1) { repository.toggleTodo(todoId) }
    }
}

