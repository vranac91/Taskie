package dvranjicic.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dvranjicic.taskie.R
import dvranjicic.taskie.ui.adapters.AboutPagerAdapter
import dvranjicic.taskie.ui.fragments.base.BaseFragment

class AboutFragment : BaseFragment() {
    override fun getLayoutResourceId() = R.layout.fragment_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById(R.id.pager) as ViewPager
        val tabLayout = view.findViewById(R.id.tabLayout) as TabLayout

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AboutPagerAdapter(childFragmentManager)
        adapter.addFragment(AboutAppFragment.newInstance(), "Apps")
        adapter.addFragment(AboutAuthorFragment.newInstance(), "Author")
        viewPager.adapter = adapter
    }

}