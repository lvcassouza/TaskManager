package com.example.taskmanager.uiView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.repository.TaskViewModel
import com.example.taskmanager.uiView.themes.TaskManagerTheme

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        // ... (Configuração básica da Activity)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        adapter = TaskAdapter { task ->
            // Lógica para editar a tarefa
        }
        recyclerview.adapter = adapter

        taskViewModel.allTasks.observe(this) { tasks ->
            adapter.submitList(tasks)
        }

        fab_add_task.setOnClickListener {
            // Iniciar AddEditTaskActivity para adicionar uma nova tarefa
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme {
        Greeting("Android")
    }
}