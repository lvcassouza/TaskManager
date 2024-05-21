package com.example.taskmanager.dataModel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task?>

    @Update
    suspend fun update(task: Task) // Add this update method

}