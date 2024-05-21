package com.example.taskmanager.repository

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanager.dataModel.Category // Or your relevant Category data class
import com.example.taskmanager.dataModel.TaskDatabase // Your Category data access object
import com.example.taskmanager.dataModel.CategoryDao

class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCategories: LiveData<List<Category>> = categoryDao.getAllCategories()

    suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    fun getCategoryById(categoryId: Int): LiveData<Category?> { // Add this method
        return categoryDao.getCategoryById(categoryId)
    }
}