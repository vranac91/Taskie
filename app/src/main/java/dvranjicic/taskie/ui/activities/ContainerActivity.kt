package dvranjicic.taskie.ui.activities

import dvranjicic.taskie.R
import dvranjicic.taskie.common.EXTRA_SCREEN_TYPE
import dvranjicic.taskie.common.EXTRA_TASK_ID
import dvranjicic.taskie.ui.activities.base.BaseActivity
import dvranjicic.taskie.ui.fragments.TaskDetailsFragment

class ContainerActivity : BaseActivity() {
    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        super.setUpUi()
        val screenType = intent.getStringExtra(EXTRA_SCREEN_TYPE)
        val id = intent.getIntExtra(EXTRA_TASK_ID, -1)
        if (screenType.isNotEmpty()) {
            when (screenType) {
                SCREEN_TASK_DETAILS -> showFragment(TaskDetailsFragment.newInstance(id))
            }
        } else finish()
    }

    companion object {
        const val SCREEN_TASK_DETAILS = "task_details"
    }


}
