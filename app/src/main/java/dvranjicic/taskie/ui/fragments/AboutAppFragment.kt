package dvranjicic.taskie.ui.fragments

import dvranjicic.taskie.R
import dvranjicic.taskie.ui.fragments.base.BaseFragment

class AboutAppFragment : BaseFragment() {
    override fun getLayoutResourceId() = R.layout.fragment_about_app

    companion object {
        fun newInstance(): AboutAppFragment = AboutAppFragment()
    }
}