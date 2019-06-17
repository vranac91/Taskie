package dvranjicic.taskie.persistence

import dvranjicic.taskie.Taskie
import dvranjicic.taskie.db.DaoProvider
import dvranjicic.taskie.db.TaskDao
import dvranjicic.taskie.model.Task

class TasksRoomRepository : TasksRepository {

    private var db: DaoProvider = DaoProvider.getInstance(Taskie.getAppContext())
    private var taskDao: TaskDao = db.taskDao()

    override fun addTask(task: Task) : Task {
        taskDao.insertTask(task)
        return task
    }

    override fun getTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    override fun getTaskBy(id: Long): Task {
        return taskDao.getTaskById(id)
    }

    override fun updateTask(id: Long) {
        taskDao.updateTask(getTaskBy(id))
    }

    override fun deleteTaskBy(id: Long) {
        taskDao.deleteTask(getTaskBy(id))
    }

    override fun clearAllTasks() {
        taskDao.deleteAllTasks()
    }

}