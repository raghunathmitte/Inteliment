package au.com.inteliment.poc.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by n1120461 on 27/04/2018.
 */
class FragmentItemAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var fragments = arrayListOf<Fragment>()
    var fragmentTitles = arrayListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitles[position]
    }
}