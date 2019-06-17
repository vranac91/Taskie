package dvranjicic.taskie.persistence

import android.preference.PreferenceManager
import dvranjicic.taskie.Taskie

object TaskPrefs {
    const val KEY_PRIORITY_NAME = "KEY_PRIORITY_NAME"

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(Taskie.getAppContext())

    fun store(key: String, value: Int){
        sharedPrefs().edit().putInt(key,value).apply()
    }

    fun getPriority(key: String, defaultValue: Int): Int {
        return sharedPrefs().getInt(key, defaultValue)
    }
}