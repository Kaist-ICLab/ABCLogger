package kaist.iclab.abclogger

import kaist.iclab.abclogger.auth.AuthRepo
import org.koin.dsl.module

val koinModule = module {
    single{
        AuthRepo(get())
    }

}