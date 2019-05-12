package dvranjicic.taskie.ui.activities

import dvranjicic.taskie.R
import dvranjicic.taskie.ui.activities.base.BaseActivity
import dvranjicic.taskie.ui.fragments.TasksFragment

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        super.setUpUi()
        supportActionBar!!.title = "Task List"
        showFragment(TasksFragment.newInstance())
    }
}