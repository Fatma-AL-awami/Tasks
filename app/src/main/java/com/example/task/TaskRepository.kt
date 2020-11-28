package com.example.task

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import database.TaskDatabase
import java.io.File
import java.util.*
import java.util.concurrent.Executors



private const val DATABASE_NAME = "TASK-database"
class TaskRepository private constructor(context: Context) {

    private val database : TaskDatabase
            = Room.databaseBuilder( context.applicationContext, TaskDatabase::class.java, DATABASE_NAME )
        .addMigrations(object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Task ADD COLUMN suspect TEXT NOT NULL DEFAULT ''")
            }
        }).build()

    private val taskDao =database.taskDao()

    private val executor = Executors.newSingleThreadExecutor()

    private val filesDir = context.applicationContext.filesDir

    fun getTask(): LiveData<List<Task>> = taskDao.getTask()
    fun getTask(id: UUID): LiveData<Task?> = taskDao.getTask(id)


    fun updateTask(task: Task) { executor.execute { taskDao.updateTask(task) } }
    fun addTask(task: Task) { executor.execute { taskDao.addTask(task) } }



    companion object {
        private var INSTANCE: TaskRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
        }



        fun get():TaskRepository {
            return INSTANCE ?:
            throw IllegalStateException("TASK Repository must be initialized")
        }
    }
}
