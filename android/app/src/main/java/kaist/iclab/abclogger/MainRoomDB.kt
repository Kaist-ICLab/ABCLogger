package kaist.iclab.abclogger

import androidx.room.Database
import androidx.room.RoomDatabase
import kaist.iclab.abclogger.collector.test.TestDao
import kaist.iclab.abclogger.collector.test.TestEntity

@Database(
    version = 1,
    entities = [
        TestEntity::class
    ],
    exportSchema = false
)
abstract class MainRoomDB: RoomDatabase() {
    abstract fun testDao(): TestDao
}