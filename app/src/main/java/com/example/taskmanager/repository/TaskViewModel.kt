package com.example.taskmanager.repository

import android.app.Application
import androidx.lifecycle.*
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.dataModel.TaskDatabase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TaskRepository = TaskRepository(TaskDatabase.getInstance(application).taskDao())
    var allTasks: LiveData<List<Task>> = repository.allTasks

    private val _taskId = MutableLiveData<Int>()
    val task: LiveData<Task?> = _taskId.switchMap { taskId ->
        repository.getTaskById(taskId)  // Directly use the LiveData from the repository
    }

    init {
        val taskDao = TaskDatabase.getInstance(application).taskDao()
        repository = TaskRepository(taskDao) // Initialize repository here
        allTasks = repository.allTasks
    }

    // Function to insert a task
    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    // Function to update a task
    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    // Function to set the taskId
    fun setTaskId(taskId: Int) {
        _taskId.value = taskId
    }
}
