package dvranjicic.taskie.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dvranjicic.taskie.R
import dvranjicic.taskie.common.EXTRA_SCREEN_TYPE
import dvranjicic.taskie.common.EXTRA_TASK_ID
import dvranjicic.taskie.common.gone
import dvranjicic.taskie.common.visible
import dvranjicic.taskie.model.Task
import dvranjicic.taskie.persistence.TasksRoomRepository
import dvranjicic.taskie.ui.activities.ContainerActivity
import dvranjicic.taskie.ui.adapters.TaskAdapter
import dvranjicic.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment : BaseFragment(), AddTaskFragmentDialog.TaskAddedListener {

    private val repository = TasksRoomRepository()
    private val adapter by lazy { TaskAdapter { onItemSelected(it) } }

    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        refreshTasks()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
    }

    private fun onItemSelected(task: Task) {
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.taskDbId)
        }
        startActivity(detailsIntent)
    }

    private fun refreshTasks() {
        progress.gone()
        val data = repository.getTasks()
        if (data.isNotEmpty()) noData.gone() else noData.visible()

        try {
            if (!requireArguments().isEmpty) {
                sortTasksByPriority()
                return
            }
        } catch (e: Exception) {
            adapter.setData(data as MutableList<Task>)
        }
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onTaskAdded(task: Task) {
        refreshTasks()
    }

    fun sortTasksByPriority() {
        val data = repository.getTasks().sortedByDescending { it.priority.ordinal }
        adapter.setData(data as MutableList<Task>)
    }

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }
    }
}