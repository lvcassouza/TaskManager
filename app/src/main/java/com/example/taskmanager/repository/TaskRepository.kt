package com.example.taskmanager.repository

import androidx.lifecycle.LiveData
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.dataModel.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    // Outros métodos (update, delete)
}