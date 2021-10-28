package com.example.medicineproject.adapters

import com.example.medicineproject.fragments.FrequentOrdersFragment
import com.example.medicineproject.fragments.RecommendationFragment
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class MyAdapter(var context: Context, fm:FragmentManager, var TotalTabs: Int):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {

        return TotalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> {
                FrequentOrdersFragment()
            }
            1->{
                RecommendationFragment()
            }
            else -> getItem(position)
        }
    }
}