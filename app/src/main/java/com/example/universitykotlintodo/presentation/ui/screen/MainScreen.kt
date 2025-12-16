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
import com.example.universitykotlintodo.presentation.viewmodel.ViewModelFactory

enum class Screen {
    TaskList,
    TaskDetail
}

@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory,
    navHostController: NavHostController = rememberNavController()
) {
    val todoViewModel: TodoViewModel = viewModel(factory = viewModelFactory)

    val state by todoViewModel.state.collectAsState()

    val detailedView = state.detailedView.let { todo ->
        state.todos.find { it.id == todo?.id }
    }

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