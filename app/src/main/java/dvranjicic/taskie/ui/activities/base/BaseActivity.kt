package dvranjicic.taskie.ui.activities.base

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dvranjicic.taskie.R
import dvranjicic.taskie.common.showFragment
import dvranjicic.taskie.ui.fragments.AboutFragment
import dvranjicic.taskie.ui.fragments.TasksFragment

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var toolbar : ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceId())
        setUpUi()
    }

    protected fun showFragment(fragment: Fragment) {
        showFragment(R.id.fragmentCont, fragment)
    }

    abstract fun getLayoutResourceId(): Int

    open fun setUpUi() {
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavBar)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationTasksList -> {
                    toolbar.title = "Task List"
                    showFragment(TasksFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationAbout -> {
                    toolbar.title = "About"
                    showFragment(AboutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
}