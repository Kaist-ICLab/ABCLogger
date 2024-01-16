package kaist.iclab.abclogger.collector.test

import android.content.Context
import android.util.Log
import kaist.iclab.abclogger.collector.AbstractCollector
import kaist.iclab.abclogger.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class TestCollector(
    private val androidContext: Context,
    private val testDao: TestDao
):AbstractCollector {
    override val TAG = javaClass.simpleName
    private var job: Job? = null

    override fun setup() {
        Log.d(TAG, "setup()")

    }
    override fun startLogging() {
        Log.d(TAG, "startLogging()")
        if(job== null){
            job = CoroutineScope(Dispatchers.IO).launch{
                while(true){
                    delay(TimeUnit.SECONDS.toMillis(5))
                    val dataReceived = System.currentTimeMillis()
                    testDao.insertTestEvent(TestEntity(
                        timestamp = dataReceived,
                        dataReceived = dataReceived,
                        deviceId =  Util.getDeviceUUID(androidContext)?: "",
                    ))
                }
            }
        }
    }
    override fun stopLogging() {
        Log.d(TAG, "stopLogging()")
        job?.cancel()
        job = null
    }

    override fun flush() {
        Log.d(TAG, "flush()")
    }
}