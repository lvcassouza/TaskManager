package com.example.taskmanager.repository

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.repository.TaskViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun TaskList(taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.allTasks.observeAsState(initial = emptyList()) // Use collectAsState to observe changes to the LiveData

    LazyColumn {
        items(tasks) { task -> // Use the tasks list directly
            TaskItem(task = task)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
