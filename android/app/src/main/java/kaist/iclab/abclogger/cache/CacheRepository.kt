package kaist.iclab.abclogger.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CacheRepository(
    private val androidContext: Context
) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    val isCollecting = booleanPreferencesKey("is_collecting")
    val isCollectingFlow: Flow<Boolean> = androidContext.dataStore.data.map {
        // On the first run of the app, we will use LinearLayoutManager by default
        it[isCollecting] ?: true
    }
}