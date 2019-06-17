package dvranjicic.taskie.persistence

import dvranjicic.taskie.model.Task

interface TasksRepository {
    fun addTask(task: Task) : Task
    fun getTasks(): List<Task>
    fun getTaskBy(id: Long): Task
    fun updateTask(id: Long)
    fun deleteTaskBy(id: Long)
    fun clearAllTasks()
}