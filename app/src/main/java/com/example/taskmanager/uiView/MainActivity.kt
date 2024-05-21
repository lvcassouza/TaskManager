package com.example.taskmanager.uiView

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.* // Import for layout components
import androidx.compose.foundation.lazy.LazyColumn // Import for LazyColumn
import androidx.compose.foundation.lazy.items // Import for LazyColumn's items
import androidx.compose.material3.* // Import for Material Design components
import androidx.compose.runtime.* // Import for state management
import androidx.compose.runtime.livedata.observeAsState // Import for observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.repository.TaskViewModel
import com.example.taskmanager.uiView.themes.TaskManagerTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskViewModel =
            ViewModelProvider(this)[TaskViewModel::class.java] // Get ViewModel instance

        setContent {
            TaskManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskList(taskViewModel = taskViewModel)
                }
            }
        }
    }
}

// TaskList Composable Function
@Composable
fun TaskList(taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.allTasks.observeAsState(initial = emptyList())

    LazyColumn {
        items(tasks) { task ->
            TaskItem(task = task)
        }
    }
}

// TaskItem Composable Function (Example)
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
