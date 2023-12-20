package kaist.iclab.abclogger

import android.app.PendingIntent
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import kaist.iclab.abclogger.auth.AuthRepo
import org.koin.android.ext.android.get

class MainActivity : ReactActivity() {

    private val authRepo by lazy { get<AuthRepo>()}

    override fun getMainComponentName(): String = "ABCLogger"
    override fun createReactActivityDelegate(): ReactActivityDelegate =
        DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)

//    override fun onStart() {
//        super.onStart()
//        login()
//    }
    private var onActivityResult: OnActivityResult? = null
    private val forActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result -> onActivityResult?.execute(result)
    }


    fun launchActivity(intent: Intent, onActivityResult_: (result:ActivityResult) -> Unit) {
        onActivityResult = object: OnActivityResult{
            override fun execute(result: ActivityResult) {
                onActivityResult_(result)
            }
        }
        forActivityResult.launch(intent)
    }

//    fun launchActivity(intent: PendingIntent, onActivityResult: (result: ActivityResult) -> Unit){
//        val activityForResult = registerForActivityResult(
//            ActivityResultContracts.StartIntentSenderForResult()){
//                result -> onActivityResult(result)
//        }
//        activityForResult.launch(IntentSenderRequest.Builder(intent).build())
//    }

//    fun login(){
//        val waitLogin = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()){
//                result -> authRepo.onLoginInfo(result)
//            }
//
//        val intent = authRepo.loginIntent(this@MainActivity)
//        waitLogin.launch(intent)
//    }

}

interface OnActivityResult{
    fun execute(result: ActivityResult)
}
