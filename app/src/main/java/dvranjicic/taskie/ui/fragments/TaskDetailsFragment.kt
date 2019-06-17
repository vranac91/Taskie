package dvranjicic.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import dvranjicic.taskie.R
import dvranjicic.taskie.common.EXTRA_TASK_ID
import dvranjicic.taskie.common.displayToast
import dvranjicic.taskie.model.Task
import dvranjicic.taskie.persistence.TasksRoomRepository
import dvranjicic.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {

    private val repository = TasksRoomRepository()
    private var taskId = NO_TASK

    override fun getLayoutResourceId() = R.layout.fragment_task_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong(EXTRA_TASK_ID)?.let { taskId = it }
        tryDisplayTask(taskId)
        editButton.setOnClickListener { editTask() }
    }

    private fun tryDisplayTask(id: Long) {
        try {
            val task = repository.getTaskBy(id)
            displayTask(task)
        } catch (e: NoSuchElementException) {
            context?.displayToast(R.string.noTaskFound)
        }
    }

    private fun displayTask(task: Task) {
        detailsTaskTitle.text = task.title
        detailsTaskDescription.text = task.description
        detailsPriorityView.setBackgroundResource(task.priority.getColor())
    }

    private fun editTask() {

    }

    companion object {
        const val NO_TASK: Long = -1

        fun newInstance(taskId: Long): TaskDetailsFragment {
            val bundle = Bundle().apply { putLong(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}