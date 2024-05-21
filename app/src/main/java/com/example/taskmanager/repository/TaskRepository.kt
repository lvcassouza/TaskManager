package com.example.taskmanager.repository

import androidx.lifecycle.LiveData
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.dataModel.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    fun getTaskById(taskId: Int): LiveData<Task?> {
        return taskDao.getTaskById(taskId)
    }

    suspend fun update(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.update(task)
        }
    }

    // Outros métodos (update, delete)
}