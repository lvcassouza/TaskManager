package com.example.taskmanager.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val categoryId: Int = 0, // Nova coluna para categoria
    val deadline: Long? = null // Data de vencimento (timestamp)
)

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
