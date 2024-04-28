package com.effmobile.aviaorders.core.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val PREFERENCES_DEPARTURE_TOWN = "PREFERENCES_DEPARTURE_TOWN"
    }

    private var preferenceManager: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    var departureUserTown: String?
        get() = preferenceManager.getString(PREFERENCES_DEPARTURE_TOWN, null)
        set(value) = preferenceManager.saveString(key = PREFERENCES_DEPARTURE_TOWN, value = value)
}

fun SharedPreferences.saveString(key: String, value: String?) {
    edit().putString(key, value).apply()
}