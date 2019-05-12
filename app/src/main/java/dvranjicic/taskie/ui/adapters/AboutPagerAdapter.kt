package dvranjicic.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AboutPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mFragments: MutableList<Fragment> = mutableListOf()
    private val mFragmentsTitles: MutableList<String> = mutableListOf()

    fun addFragment(fragment: Fragment, title: String) {
        mFragments. add(fragment)
        mFragmentsTitles.add(title)
    }

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount(): Int = mFragments.size

    override fun getPageTitle(position: Int): CharSequence? = mFragmentsTitles[position]
}

