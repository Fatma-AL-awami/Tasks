package database

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.task.Task

@Database(entities = [ Task::class ], version=1 , exportSchema = false)
@TypeConverters(TaskTypeConverters::class)

 abstract class TaskDatabase :RoomDatabase() {

    abstract fun taskDao(): TaskDao
    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE Task ADD COLUMN suspect TEXT NOT NULL DEFAULT ''"
            )
        }
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}