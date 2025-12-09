package com.example.universitykotlintodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.universitykotlintodo.data.local.TodoJsonDataSource
import com.example.universitykotlintodo.data.repository.TodoRepositoryImpl
import com.example.universitykotlintodo.domain.usecase.GetTodosUseCase
import com.example.universitykotlintodo.domain.usecase.ToggleTodoUseCase
import com.example.universitykotlintodo.presentation.ui.screen.MainScreen
import com.example.universitykotlintodo.presentation.ui.theme.UniversityKotlinToDoTheme
import com.example.universitykotlintodo.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val localSource = TodoJsonDataSource(this)
        val repository = TodoRepositoryImpl(localSource)
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)

        val todoViewModel = TodoViewModel(
            getTodosUseCase = getTodosUseCase,
            toggleTodoUseCase = toggleTodoUseCase
        )


        setContent {
            UniversityKotlinToDoTheme {
                MainScreen(
                    todoViewModel = todoViewModel
                )
            }
        }
    }
}