package kaist.iclab.abclogger

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import kaist.iclab.abclogger.auth.AuthReactModule
import kaist.iclab.abclogger.auth.AuthRepo
import kaist.iclab.abclogger.collector.CollectorReactModule
import kaist.iclab.abclogger.collector.CollectorRepository
import kaist.iclab.abclogger.maintenance.MaintenanceReactModule
import kaist.iclab.abclogger.maintenance.MaintenanceRepo

class MainReactPackage(
    private val authRepo: AuthRepo,
    private val maintenanceRepo: MaintenanceRepo,
    private val collectorRepository: CollectorRepository
) : ReactPackage {
    override fun createViewManagers(p0: ReactApplicationContext): MutableList<ViewManager<View, ReactShadowNode<*>>> =
        mutableListOf()

    override fun createNativeModules(reactApplicationContext: ReactApplicationContext): MutableList<NativeModule> {
        return mutableListOf(
            AuthReactModule(reactApplicationContext, authRepo),
            MaintenanceReactModule(reactApplicationContext, maintenanceRepo),
            CollectorReactModule(reactApplicationContext, collectorRepository)
        )
    }
}