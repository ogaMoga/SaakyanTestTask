package com.ogamoga.developerslive.screens.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ogamoga.developerslive.App
import com.ogamoga.developerslive.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMode()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun setMode() {
        val appSharedPrefs: SharedPreferences = getSharedPreferences(App.IS_DARK_MODE, 0)
        if ((android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) && (!appSharedPrefs.contains(App.DARK_MODE_KEY))) {
            return
        }

        val isNightModeOn: Boolean = appSharedPrefs.getBoolean(App.DARK_MODE_KEY, false)
        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}