package data

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import java.io.File

object DatabaseBuilder {
   private  fun getDatabaseBuilder(): RoomDatabase.Builder<AppDataBase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "travel_agency.db")
        return Room.databaseBuilder<AppDataBase>(
            name = dbFile.absolutePath,
        )
    }

   private fun getRoomDatabase(
        builder: RoomDatabase.Builder<AppDataBase>
    ): AppDataBase {
        return builder
            .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = false)
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

//    singleton instance of the database
    private var database: AppDataBase? = null

    fun getDatabase(): AppDataBase {
        if (database == null) {
            database = getRoomDatabase(getDatabaseBuilder())
        }
        return database!!
    }
}
