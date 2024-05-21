package com.example.taskmanager.uiView

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.repository.TaskItem
import com.example.taskmanager.repository.TaskList
import com.example.taskmanager.repository.TaskViewModel
import com.example.taskmanager.uiView.themes.TaskManagerTheme
import androidx.navigation.NavHostController // Add for navController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // Surface and navController moved to AppNavigation
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = "task_list") {
            // Route for TaskListScreen
            composable("add_edit_task/{taskId}") { backStackEntry ->
                val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: -1
                AddEditTaskScreen(navController, taskViewModel, taskId)
            }
            // Other routes can be added here for different screens
        }
    }
}

@Composable
fun TaskListScreen(navController: NavHostController, taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.allTasks.observeAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_edit_task") }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(tasks) { task ->
                TaskItem(task = task)
            }
        }
    }
}


@Composable
fun TaskItem(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = task.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
        // ... (add other task details like due date, completion status, etc.)
    }
}
