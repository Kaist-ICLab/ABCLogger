package kaist.iclab.abclogger

import androidx.room.Room
import kaist.iclab.abclogger.auth.AuthRepo
import kaist.iclab.abclogger.collector.CollectorRepository
import kaist.iclab.abclogger.collector.test.TestCollector
import kaist.iclab.abclogger.maintenance.MaintenanceRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val koinModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MainRoomDB::class.java,
            "MainRoomDB"
        )
            .fallbackToDestructiveMigration() // For Dev Phase!
            .build()
    }
    single{
        AuthRepo(get())
    }
    single{
        MaintenanceRepo(get())
    }

    single {
        TestCollector(get(), get<MainRoomDB>().testDao())
    }

    single {
        CollectorRepository(get(), listOf(
            get<TestCollector>()
        ))
    }
}