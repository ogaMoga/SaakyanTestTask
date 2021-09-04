package com.ogamoga.developerslive.screens.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ogamoga.developerslive.screens.item.ItemFragment

class PagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        ItemFragment.newInstance(position)
}