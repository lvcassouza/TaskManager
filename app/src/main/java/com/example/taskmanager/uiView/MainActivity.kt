package com.example.taskmanager.uiView

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.repository.TaskViewModel
import com.example.taskmanager.uiView.themes.TaskManagerTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        adapter = TaskAdapter { task ->
            // Lógica para editar a tarefa
        }
        val recyclerView: RecyclerView = findViewById(R.id.your_recycler_view_id)

        taskViewModel.allTasks.observe(this) { tasks ->
            adapter.submitList(tasks)
        }

        val fabAddTask: FloatingActionButton = findViewById(R.id.your_fab_add_task_id)

        recyclerView.adapter = adapter

        fabAddTask.setOnClickListener {
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