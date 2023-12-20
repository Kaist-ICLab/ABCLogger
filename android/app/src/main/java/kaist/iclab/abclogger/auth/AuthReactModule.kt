package kaist.iclab.abclogger.auth

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import kaist.iclab.abclogger.MainActivity


class AuthReactModule(
    reactApplicationContext: ReactApplicationContext,
    val authRepo: AuthRepo
) : ReactContextBaseJavaModule(reactApplicationContext) {
    private val TAG = javaClass.simpleName
    override fun getName(): String = TAG

//    private class OnActivityResultImpl(val authRepo: AuthRepo):OnActivityResult{
//        override fun execute(result: ActivityResult) {
//            authRepo.onLoginInfo(result)
//        }
//    }
    @ReactMethod
    fun login() {
        (currentActivity as MainActivity?)?.let { activity ->
            authRepo.login(activity)
//            activity.launchActivity(authRepo.login(activity)){ result ->
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                try{
//                    val account = task.getResult(ApiException::class.java)
//
//                    // 이름, 이메일 등이 필요하다면 아래와 같이 account를 통해 각 메소드를 불러올 수 있다.
//                    val userName = account.email
//
//                    Log.e(TAG, "SUCCESS: $userName")
//                } catch(e: ApiException){
//                    Log.e(TAG, "FAIL: $e")
//                }
//            }

//            activity.onActivityResult = OnActivityResultImpl(authRepo)
//            activity.activityForResult.launch(intent)
        }
    }

    @ReactMethod
    fun logout() {
        authRepo.logout()
    }
//    @ReactMethod
//    fun addListener(eventName: String){
//        when(eventName){
//            userInfoEvent -> addUserInfoListener()
//        }
//    }
//    @ReactMethod
//    fun removeListeners(count: Int){
//       removeUserInfoListener(count)
//    }

//    private var userInfoFlow: Flow<FirebaseUser?>? = null
//    private var userInfoFlowCoroutine: Job? = null
//    private var userInfoListenerCount = 0
//    private val userInfoEvent = "userInfo"
//    private fun addUserInfoListener(){
//        if(userInfoListenerCount == 0){
//            userInfoFlow = authRepo.getUserInfo()
//            userInfoFlowCoroutine= CoroutineScope(Dispatchers.IO).launch{
//                userInfoFlow?.collect{
//                    val email = it?.email
//                    reactApplicationContext
//                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
//                        .emit("userInfo", email?:"null")
//                }
//            }
//        }
//        userInfoListenerCount++
//    }
//    private fun removeUserInfoListener(count: Int){
//        userInfoListenerCount -= count
//        if(userInfoListenerCount <= 0){
//            userInfoListenerCount = 0
//            userInfoFlowCoroutine?.cancel()
//        }
//    }

}