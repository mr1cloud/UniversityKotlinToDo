package com.example.universitykotlintodo.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.universitykotlintodo.presentation.viewmodel.TodoViewModel

enum class Screen {
    TaskList,
    TaskDetail
}

@Composable
fun MainScreen(
    todoViewModel: TodoViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val state by todoViewModel.state.collectAsState()

    NavHost(navHostController, startDestination = Screen.TaskList.name) {
        composable(Screen.TaskList.name) {
            TaskListScreen(
                todos = state.todos,
                onTaskClick = { todo ->
                    todoViewModel.setDetailedView(todo)
                    navHostController.navigate(Screen.TaskDetail.name)
                },
                onTaskCheckedChange = { todo, _ ->
                    todoViewModel.toggleTodo(todo.id)
                }
            )
        }
        composable(Screen.TaskDetail.name) {
            val detailedView = state.detailedView
            if (detailedView != null) {
                TaskDetailScreen(
                    task = detailedView,
                    onBackClick = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}