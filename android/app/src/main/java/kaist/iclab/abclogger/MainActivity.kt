package kaist.iclab.abclogger

import androidx.activity.result.ActivityResult
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

    var onActivityResult: OnActivityResult? = null
    val activityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result -> onActivityResult?.execute(result)
    }
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
