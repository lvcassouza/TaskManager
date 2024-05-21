package com.example.taskmanager.dataModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCategories: LiveData<List<Category>> = categoryDao.getAllCategories()

    suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    fun getCategoryById(categoryId: Int): LiveData<Category?> {
        return categoryDao.getCategoryById(categoryId)
    }
}