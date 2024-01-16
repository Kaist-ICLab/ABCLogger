package kaist.iclab.abclogger.collector

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class CollectorReactModule(
    reactApplicationContext: ReactApplicationContext,
    val collectorRepository: CollectorRepository
): ReactContextBaseJavaModule(reactApplicationContext) {
    private val TAG = javaClass.simpleName
    override fun getName(): String = TAG

    @ReactMethod
    fun start(){
        collectorRepository.start()
    }

    @ReactMethod
    fun stop(){
        collectorRepository.stop()
    }

}