package kaist.iclab.abclogger.collector.test

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TestDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTestEvent(testEntity: TestEntity)
}