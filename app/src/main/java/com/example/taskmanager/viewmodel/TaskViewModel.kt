package com.example.taskmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskmanager.dataModel.Category
import com.example.taskmanager.dataModel.TaskDatabase

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    // ...
    private val categoryRepository: CategoryRepository

    init {
        // ...
        val categoryDao = TaskDatabase.getDatabase(application).categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun getAllCategories(): LiveData<List<Category>> = categoryRepository.allCategories

    // ...
}