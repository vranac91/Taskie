package dvranjicic.taskie.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import dvranjicic.taskie.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks() : List<Task>

    @Query("SELECT * FROM TASK WHERE taskDbId = :taskId")
    fun getTaskById(taskId: Long) : Task

    @Insert(onConflict = IGNORE)
    fun insertTask(task: Task) : Long

    @Update(onConflict = REPLACE)
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM Task")
    fun deleteAllTasks()

}