package kaist.iclab.abclogger.collector.test

import android.os.Build
import android.provider.Settings
import androidx.room.Entity
import androidx.room.PrimaryKey
import kaist.iclab.abclogger.BuildConfig


@Entity(
    tableName = "test"
)
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val appVersion: String = "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})",
    val deviceId: String,
    val dataReceived: Long,
    var dataUploaded: Long? = null,
    val timestamp: Long
)