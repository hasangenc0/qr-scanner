package com.hasan.qrScanner.ui.home

import android.content.Context
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hasan.qrScanner.R

class HomeAdapter(private val context: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf(context.getString(R.string.checking_tab),context.getString(R.string.confirm_tab) )

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                //  val homeFragment: CheckingFragment = CheckingFragment()
                return CheckingFragment()
            }
            1 -> {
                return ConfirmFragment()
            }
            else -> return null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }


    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}
