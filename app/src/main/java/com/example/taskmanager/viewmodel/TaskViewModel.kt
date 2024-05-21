package com.example.taskmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskmanager.dataModel.Category
import com.example.taskmanager.dataModel.TaskDatabase
import com.example.taskmanager.repository.CategoryRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    // ...
    private val categoryRepository: CategoryRepository

    init {
        // ...
        val categoryDao = TaskDatabase.getInstance(application).categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun getAllCategories(): LiveData<List<Category>> = categoryRepository.allCategories

    // ...
}