package com.example.pornhub.Adapter

import android.icu.text.CaseMap.Title
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager : FragmentManager) : FragmentPagerAdapter(supportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private  val Fragmentlist = ArrayList<Fragment>()
    private val FragmenTittleList = ArrayList<String>()

    override fun getCount(): Int {
        return Fragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
        return Fragmentlist[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return FragmenTittleList[position]
    }

    fun addFragment(fragment: Fragment, title: String){
        Fragmentlist.add(fragment)
        FragmenTittleList.add(title)
    }
}