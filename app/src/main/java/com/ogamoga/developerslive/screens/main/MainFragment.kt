package com.ogamoga.developerslive.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ogamoga.developerslive.R

class MainFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__main, container, false)
        rootView.apply {
            tabLayout = findViewById(R.id.fragment__main__tablayout)
            viewPager = findViewById(R.id.fragment__main__viewpager)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.latest_tab_title)
                1 -> getString(R.string.top_tab_title)
                2 -> getString(R.string.hot_tab_title)
                else -> ""
            }
        }.attach()
    }
    companion object {
        fun newInstance(): Fragment = MainFragment()
    }
}