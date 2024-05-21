package com.example.taskmanager.repository

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.liveData
import com.example.taskmanager.dataModel.Category
import com.example.taskmanager.dataModel.Task
import com.example.taskmanager.dataModel.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository = TaskRepository(TaskDatabase.getInstance(application).taskDao())
    private val categoryRepository: CategoryRepository = CategoryRepository(TaskDatabase.getInstance(application).categoryDao())

    val allTasks: LiveData<List<Task>> = liveData {
        emitSource(repository.allTasks)
    }
    private val _taskId = MutableLiveData<Int>()
    val task: LiveData<Task?> = _taskId.switchMap { taskId -> repository.getTaskById(taskId) }

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

    fun getAllCategories() = categoryRepository.allCategories

    fun getCategoryById(categoryId: Int): LiveData<Category?> {
        return categoryRepository.getCategoryById(categoryId)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    fun deleteCategory(category: Category) = viewModelScope.launch {
        categoryRepository.deleteCategory(category)
    }

    suspend fun getTask(taskId: Int): Task? = withContext(Dispatchers.IO) {
        repository.getTaskById(taskId).value
    }
//    fun getTaskById(taskId: Int): LiveData<Task?> = liveData {
//        emit(repository.getTaskById(taskId))
//    }
}
