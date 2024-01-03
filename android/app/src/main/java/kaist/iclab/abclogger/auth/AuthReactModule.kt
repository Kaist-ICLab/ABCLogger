package kaist.iclab.abclogger.auth

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kaist.iclab.abclogger.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.RuntimeException


class AuthReactModule(
    reactApplicationContext: ReactApplicationContext,
    val authRepo: AuthRepo
) : ReactContextBaseJavaModule(reactApplicationContext) {
    private val TAG = javaClass.simpleName
    private var listenerCount = 0
    private var listener: Job? = null
    override fun getName(): String = TAG

    @ReactMethod
    fun login() {
        (currentActivity as MainActivity?)?.let { activity ->
            authRepo.login(activity)
        }
    }

    @ReactMethod
    fun logout() {
        authRepo.logout()
    }

    private fun sendEvent(eventName: String, params: WritableMap?) {
        reactApplicationContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

    @ReactMethod
    fun addListener(eventName: String) {
        if (listenerCount == 0) {
            if (listener == null) {
                listener = CoroutineScope(Dispatchers.IO).launch {
                    authRepo.currentUserFlow().collect {
                        val map = WritableNativeMap()
                        map.putBoolean("isNull", it == null)
                        map.putString("name", it?.displayName ?: "")
                        map.putString("email", it?.email ?: "")
                        sendEvent(eventName, map)
                    }
                }
            }
        }
        listenerCount++
    }

    @ReactMethod
    fun removeListeners(count: Int) {
        listenerCount -= count
        if (listenerCount == 0) {
            listener?.cancel()
            listener = null
        }
    }
    @ReactMethod
    fun testCrashFunc(){
        Log.d(TAG, "TEST")
        Firebase.crashlytics.log("TEST CRASH")
        Firebase.crashlytics.recordException(RuntimeException("TEST CRASH"))
    }
}