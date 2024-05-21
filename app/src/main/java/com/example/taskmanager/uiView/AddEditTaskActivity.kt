package com.example.taskmanager.uiView

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.R
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.repository.TaskViewModel

class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var buttonSave: Button
    private lateinit var taskViewModel: TaskViewModel
    private var taskId = -1 // Initialize taskId outside onCreate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        // Initialize views
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        buttonSave = findViewById(R.id.button_save)

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Get taskId from intent
        taskId = intent.getIntExtra("TASK_ID", -1)

        if (taskId != -1) {
            taskViewModel.setTaskId(taskId) // Trigger task fetching
            taskViewModel.task.observe(this) { task ->
                task?.let { // Only proceed if task is not null
                    editTextTitle.setText(it.title)
                    editTextDescription.setText(it.description)
                }
            }
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
                val task = Task(title = title, description = description)
                if (taskId == -1) {
                    // Insert a new task
                    taskViewModel.insert(task)
                } else {
                    // Update existing task
                    task.id = taskId
                    taskViewModel.update(task)
                }
                finish() // Close the activity after saving
            } else {
                // Handle case where title is blank (e.g., show error message)
            }
        }
    }
}