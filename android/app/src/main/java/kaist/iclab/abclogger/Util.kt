package kaist.iclab.abclogger

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object Util{
    @SuppressLint("HardwareIds")
    fun getDeviceUUID(context: Context): String?{
//        It is not recommeded to retreive Hard
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}