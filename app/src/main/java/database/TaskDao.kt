package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.task.Task
import java.util.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    fun getTask(): LiveData<List<Task>>
    @Query("SELECT * FROM Task WHERE id=(:id)")
    fun getTask(id: UUID): LiveData<Task?>
    @Update
    fun updateTask(task: Task)
    @Insert
    fun addTask(task:Task)
}