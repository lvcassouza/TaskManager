package com.example.taskmanager.uiView

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.repository.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun AddEditTaskScreen(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    taskId: Int = -1
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // If editing an existing task, fetch and populate the fields
    if (taskId != -1) {
        LaunchedEffect(taskId) {
            val task = taskViewModel.getTask(taskId) // Fetches the task
            task?.let {
                title = it.title
                description = it.description
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val newTask = Task(title = title, description = description)
                if (taskId == -1) {
                    taskViewModel.insert(newTask)
                } else {
                    taskViewModel.update(newTask.copy(id = taskId))
                }
                navController.navigate("task_list")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (taskId == -1) "Add Task" else "Update Task")
        }
    }
}
