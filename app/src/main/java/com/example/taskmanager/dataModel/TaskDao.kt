package com.example.taskmanager.dataModel

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun insertTask(task: Task): Long

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task?>

    @Update
    fun update(task: Task): Int

    @Delete
    fun deleteTask(task: Task)
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<Category>>

    @Insert
    fun insertCategory(category: Category): Long

    @Delete
    fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getCategoryById(categoryId: Int): LiveData<Category?>
}