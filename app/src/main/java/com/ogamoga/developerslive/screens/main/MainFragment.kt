package com.ogamoga.developerslive.screens.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ogamoga.developerslive.App
import com.ogamoga.developerslive.R

class MainFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var themeButton: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__main, container, false)
        rootView.apply {
            tabLayout = findViewById(R.id.fragment__main__tablayout)
            viewPager = findViewById(R.id.fragment__main__viewpager)
            themeButton = findViewById(R.id.fragment__main__theme_btn)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeButton.setOnClickListener { changeTheme() }

        viewPager.adapter = PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.latest_tab_title)
                1 -> getString(R.string.top_tab_title)
                2 -> getString(R.string.random_tab_title)
                else -> ""
            }
        }.attach()
    }

    private fun changeTheme() {
        val appSharedPrefs: SharedPreferences = requireActivity().getSharedPreferences(App.IS_DARK_MODE, 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSharedPrefs.edit()
        val isNightModeOn: Boolean = appSharedPrefs.getBoolean(App.DARK_MODE_KEY, false)
        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPrefsEdit.putBoolean(App.DARK_MODE_KEY, false)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPrefsEdit.putBoolean(App.DARK_MODE_KEY, true)
        }
        sharedPrefsEdit.apply()
    }

    companion object {
        fun newInstance(): Fragment = MainFragment()
    }
}