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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        buttonSave = findViewById(R.id.button_save)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val taskId = intent.getIntExtra("TASK_ID", -1)
        if (taskId != -1) {
            // Modo de edição: preencher os campos com os dados da tarefa
            taskViewModel.getTaskById(taskId).observe(this) { task ->
                editTextTitle.setText(task.title)
                editTextDescription.setText(task.description)
            }
        }

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()

            if (title.isNotBlank()) {
                val task = Task(title = title, description = description)
                if (taskId == -1) {
                    taskViewModel.insert(task)
                } else {
                    task.id = taskId
                    taskViewModel.update(task)
                }
                finish()
            }
        }
    }
}
