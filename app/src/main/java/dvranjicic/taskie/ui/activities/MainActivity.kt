package dvranjicic.taskie.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import dvranjicic.taskie.R
import dvranjicic.taskie.persistence.TasksRoomRepository
import dvranjicic.taskie.ui.activities.base.BaseActivity
import dvranjicic.taskie.ui.fragments.AboutFragment
import dvranjicic.taskie.ui.fragments.TasksFragment

class MainActivity : BaseActivity() {

    private val repository = TasksRoomRepository()
    private lateinit var toolbar : ActionBar

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        supportActionBar?.let { it.title = getString(R.string.taskListTitle) }
        toolbar = supportActionBar!!
        showFragment(TasksFragment.newInstance())
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavBar)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationTasksList -> {
                    toolbar.title = getString(R.string.taskListTitle)
                    showFragment(TasksFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationAbout -> {
                    toolbar.title = getString(R.string.aboutTitle)
                    showFragment(AboutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_tasks, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.removeTasksItem -> showClearAllDialog()
            R.id.sortByPriorityItem -> {
                val bundle = Bundle().apply { putString("shouldBeSorted", "true") }
                showFragment(TasksFragment.newInstance().apply { arguments = bundle })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showClearAllDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.removeTasksMenuItem))
            .setMessage(getString(R.string.removeTasksAlertText))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                repository.clearAllTasks()
                recreate()
            }
            .setNegativeButton(android.R.string.no, null).show()
    }
}