package kaist.iclab.abclogger.cache

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class CacheReactModule(
    reactApplicationContext: ReactApplicationContext,
    private val cacheRepository: CacheRepository
): ReactContextBaseJavaModule() {
    private val TAG = javaClass.simpleName
    override fun getName(): String = TAG

    @ReactMethod
    suspend fun collectStarted(){
    }

    @ReactMethod
    suspend fun collectStopped(){

    }

}