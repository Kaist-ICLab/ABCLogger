package kaist.iclab.abclogger.maintenance

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.telephony.mbms.DownloadRequest
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.RECEIVER_EXPORTED
import androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.registerReceiver
import androidx.core.net.toUri
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kaist.iclab.abclogger.BuildConfig
import kaist.iclab.abclogger.MainActivity


class MaintenanceRepo(
    private val context: Context
) {

    val TAG = javaClass.simpleName
    val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    init {
        Firebase.crashlytics.setUserId(null.toString())

        remoteConfig.setConfigSettingsAsync(remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        })
//       It is for realtime. currently, I don't think it is needed
//        remoteConfig.addOnConfigUpdateListener(object: ConfigUpdateListener {
//            override fun onUpdate(configUpdate: ConfigUpdate) {
//                if(configUpdate.updatedKeys.contains("versionCode")){
//                    remoteConfig.activate().addOnCompleteListener {
//                        if(it.isSuccessful){
//                            checkUpdate()
//                        }
//                    }
//                }
//            }
//            override fun onError(error: FirebaseRemoteConfigException) {
//                Firebase.crashlytics.recordException(error)
//            }
//        })
    }

    fun checkUpdate(onUpdateListener: (Boolean) -> Unit) {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                val latestVersionCode = remoteConfig.getLong("versionCode")
                Log.d(TAG, "latestVersionCode: $latestVersionCode")
                onUpdateListener(latestVersionCode > BuildConfig.VERSION_CODE)
            }
        }
    }
    fun update() {
        val latestVersionName = remoteConfig.getString("versionName")
        Log.d(TAG, "latestVersionName: $latestVersionName")
        val url =
            "https://github.com/Kaist-ICLab/ABCLogger/releases/download/v${latestVersionName}/app-release.apk"
        val installIntent = Intent(Intent.ACTION_VIEW, url.toUri())
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(installIntent)
    }
//        val receiver = registerReceiver(
//            context,
//            object : BroadcastReceiver() {
//                override fun onReceive(context: Context?, intent: Intent?) {
//                    Log.d(TAG, "on ACTION DOWNLOAD Complete")
//                    val installIntent = Intent(context, MainActivity::class.java)
//                    installIntent.action = Intent.ACTION_VIEW
//                    context?.let {
////                        installIntent.setClass(it, MainActivity::class.java)
////                    installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        val dest = "${
//                            it.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
//                        }/ABCLogger-v${latestVersionName}.apk"
//                        installIntent.setDataAndType(
//                            dest.toUri(),
//                            "application/vnd.android.package-archive"
//                        )
//                        it.startActivity(installIntent)
//                        it.unregisterReceiver(this)
//                    }
//                }
//            },
//            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
//            RECEIVER_EXPORTED
//        )
//        return downloadManager.enqueue(request)

    fun downloadUpdate(): Long {
        val latestVersionName = remoteConfig.getString("versionName")
        Log.d(TAG, "latestVersionName: $latestVersionName")
        val url =
            "https://github.com/Kaist-ICLab/ABCLogger/releases/download/v${latestVersionName}/app-release.apk"

        val downloadManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getSystemService(DownloadManager::class.java)
        } else {
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        }
        val request = DownloadManager.Request(url.toUri())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setTitle("Download Updated Version")
            .setDescription("Downloading file...")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "ABCLogger-v${latestVersionName}.apk"
            )

        val receiver = registerReceiver(
            context,
            object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    Log.d(TAG, "on ACTION DOWNLOAD Complete")
                    val installIntent = Intent(context, MainActivity::class.java)
                    installIntent.action = Intent.ACTION_VIEW
                    context?.let {
//                        installIntent.setClass(it, MainActivity::class.java)
//                    installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        val dest = "${
                            it.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
                        }/ABCLogger-v${latestVersionName}.apk"
                        installIntent.setDataAndType(
                            dest.toUri(),
                            "application/vnd.android.package-archive"
                        )
                        it.startActivity(installIntent)
                        it.unregisterReceiver(this)
                    }
                }
            },
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            RECEIVER_EXPORTED
        )
        return downloadManager.enqueue(request)

    }
}