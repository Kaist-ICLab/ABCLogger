package kaist.iclab.abclogger

import kaist.iclab.abclogger.auth.AuthRepo
import kaist.iclab.abclogger.maintenance.MaintenanceRepo
import org.koin.dsl.module

val koinModule = module {
    single{
        AuthRepo(get())
    }
    single{
        MaintenanceRepo(get())
    }

}