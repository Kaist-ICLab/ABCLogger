package kaist.iclab.abclogger.collector

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import org.koin.android.ext.android.inject

class CollectorService : Service() {

    private val collectorRepository by inject<CollectorRepository>()
    private val TAG = javaClass.simpleName
    private val CHANNEL_ID_NAME = "COLLECTOR_WORKING"
    private val CHANNEL_ID = 1
    private val TITLE = "Collector Working..."
    private val DESCRIPTION = "Collector is tracking for your daily life!"
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        createNotificationChannel()
        collectorRepository.collectors.forEach {
            it.startLogging()
        }
        val notification: Notification =
            NotificationCompat.Builder(this, CHANNEL_ID_NAME)
                .setContentTitle(TITLE)
                .setContentText(DESCRIPTION)
                .build()
        startForeground(CHANNEL_ID, notification)
        return START_STICKY
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onDestroy()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d(TAG, "onTaskRemoved()")
        collectorRepository.collectors.forEach {
            it.stopLogging()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID_NAME, TITLE, importance).apply {
                description = DESCRIPTION
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }else{
            Log.d(TAG, "Notification Channel is not created due to Android Version: ${Build.VERSION.SDK_INT}")
        }
    }
}