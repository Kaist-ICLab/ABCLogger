package kaist.iclab.abclogger.auth

import android.app.Activity
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import androidx.activity.result.ActivityResult
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.google.firebase.auth.FirebaseUser
import kaist.iclab.abclogger.MainActivity
import kaist.iclab.abclogger.OnActivityResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class AuthReactModule(
    reactApplicationContext: ReactApplicationContext,
    val authRepo: AuthRepo
) : ReactContextBaseJavaModule(reactApplicationContext) {
    private val TAG = javaClass.simpleName
    override fun getName(): String = TAG

    private class OnActivityResultImpl(val authRepo: AuthRepo):OnActivityResult{
        override fun execute(result: ActivityResult) {
            authRepo.onLoginInfo(result)
        }
    }
    @ReactMethod
    fun login() {
        currentActivity?.let { activity ->
            val intent = authRepo.loginIntent(activity)
            intent?.let{
                (activity as MainActivity).onActivityResult = OnActivityResultImpl(authRepo)
                activity.activityForResult.launch(intent)
            }
        }
    }

    @ReactMethod
    fun logout() {
        authRepo.logout()
    }
    @ReactMethod
    fun addListener(eventName: String){
        when(eventName){
            userInfoEvent -> addUserInfoListener()
        }
    }
    @ReactMethod
    fun removeListeners(count: Int){
       removeUserInfoListener(count)
    }

    private var userInfoFlow: Flow<FirebaseUser?>? = null
    private var userInfoFlowCoroutine: Job? = null
    private var userInfoListenerCount = 0
    private val userInfoEvent = "userInfo"
    private fun addUserInfoListener(){
        if(userInfoListenerCount == 0){
            userInfoFlow = authRepo.getUserInfo()
            userInfoFlowCoroutine= CoroutineScope(Dispatchers.IO).launch{
                userInfoFlow?.collect{
                    val email = it?.email
                    reactApplicationContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                        .emit("userInfo", email?:"null")
                }
            }
        }
        userInfoListenerCount++
    }
    private fun removeUserInfoListener(count: Int){
        userInfoListenerCount -= count
        if(userInfoListenerCount <= 0){
            userInfoListenerCount = 0
            userInfoFlowCoroutine?.cancel()
        }
    }

}