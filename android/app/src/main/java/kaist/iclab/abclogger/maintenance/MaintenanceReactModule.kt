package kaist.iclab.abclogger.maintenance

import android.app.DownloadManager
import android.net.Uri
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import kaist.iclab.abclogger.BuildConfig


class MaintenanceReactModule(
    reactApplicationContext: ReactApplicationContext,
    val maintenanceRepo: MaintenanceRepo
) : ReactContextBaseJavaModule(reactApplicationContext) {
    private val TAG = javaClass.simpleName
    override fun getName(): String = TAG

    @ReactMethod
    fun checkUpdate(onUpdate: Callback){
        maintenanceRepo.checkUpdate {
            if(it){
                onUpdate()
            }
        }
    }

    @ReactMethod
    fun update(){
        maintenanceRepo.update()
    }


}