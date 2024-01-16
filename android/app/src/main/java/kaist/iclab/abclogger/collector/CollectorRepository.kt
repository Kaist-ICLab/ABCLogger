package kaist.iclab.abclogger.collector

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat

class CollectorRepository(
    val androidContext: Context,
    val collectors: List<AbstractCollector>,

) {
    private val TAG = javaClass.simpleName
    private val serviceIntent = Intent(androidContext, CollectorService::class.java)

    fun start() {
        Log.d(TAG, "start()")
        ContextCompat.startForegroundService(androidContext, serviceIntent)
    }

    fun stop() {
        Log.d(TAG, "stop()")
        androidContext.stopService(serviceIntent)
    }

    fun flush(){
        collectors.forEach {
            it.flush()
        }
    }
}