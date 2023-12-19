package kaist.iclab.abclogger

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import kaist.iclab.abclogger.auth.AuthReactModule
import kaist.iclab.abclogger.auth.AuthRepo

class MainReactPackage(
    private val authRepo: AuthRepo
):ReactPackage {
    override fun createViewManagers(p0: ReactApplicationContext): MutableList<ViewManager<View, ReactShadowNode<*>>>  = mutableListOf()
    override fun createNativeModules(reactApplicationContext: ReactApplicationContext): MutableList<NativeModule> {
        return mutableListOf(
            AuthReactModule(reactApplicationContext,authRepo)
        )
    }


}