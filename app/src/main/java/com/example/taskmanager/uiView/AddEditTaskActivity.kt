package com.example.taskmanager.uiView

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.R
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.repository.TaskViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var buttonSave: Button
    private lateinit var taskViewModel: TaskViewModel
    private var taskId = -1 // Initialize taskId outside onCreate
    private var isNewTask: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // Initialize views
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        buttonSave = findViewById(R.id.button_save)

        // Get taskId from intent
        taskId = intent.getIntExtra("TASK_ID", -1)

        if (taskId != -1) {
            isNewTask = false
            lifecycleScope.launch { // Launch coroutine in lifecycleScope
                val task = taskViewModel.getTask(taskId) // Fetch task synchronously
                task?.let {
                    editTextTitle.setText(it.title)
                    editTextDescription.setText(it.description)
                }
            }
        } else {
            isNewTask = true // This is a new task
        }

        taskViewModel.task.observe(this) { task ->
            task?.let { // Only proceed if task is not null
                editTextTitle.setText(it.title)
                editTextDescription.setText(it.description)
            }
        }

        // Set click listener for the save button
        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()

            if (title.isNotBlank()) {
                // Create a new task object (avoids reassignment issue)
                val newTask = Task(id = if (isNewTask) 0 else taskId, title = title, description = description)

                if (isNewTask) {
                    // Insert a new task
                    taskViewModel.insert(newTask)
                } else {
                    // Update existing task
                    taskViewModel.update(newTask)
                }
                finish() // Close the activity after saving
            } else {
                // Handle case where title is blank (e.g., show error message)
            }
        }
    }
}