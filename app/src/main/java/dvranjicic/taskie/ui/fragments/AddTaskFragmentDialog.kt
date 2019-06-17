package dvranjicic.taskie.ui.fragments

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import dvranjicic.taskie.R
import dvranjicic.taskie.common.displayToast
import dvranjicic.taskie.model.Priority
import dvranjicic.taskie.model.Task
import dvranjicic.taskie.persistence.TaskPrefs
import dvranjicic.taskie.persistence.TasksRoomRepository
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*

class AddTaskFragmentDialog : DialogFragment() {

    private var taskAddedListener: TaskAddedListener? = null
    private val repository = TasksRoomRepository()
    private val currentPriority = TaskPrefs.getPriority(TaskPrefs.KEY_PRIORITY_NAME, 0)

    interface TaskAddedListener {
        fun onTaskAdded(task: Task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    fun setTaskAddedListener(listener: TaskAddedListener) {
        taskAddedListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_new_task, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
    }

    private fun initUi() {
        context?.let {
            prioritySelector.adapter = ArrayAdapter<Priority>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                Priority.values())
            prioritySelector.setSelection(currentPriority)
        }
    }

    private fun initListeners() {
        saveTaskAction.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        if (isInputEmpty()) {
            context?.displayToast(getString(R.string.emptyFields))
            return
        }

        val title = newTaskTitleInput.text.toString()
        val description = newTaskDescriptionInput.text.toString()
        val priority = prioritySelector.selectedItem as Priority

        TaskPrefs.store(TaskPrefs.KEY_PRIORITY_NAME, prioritySelector.selectedItemPosition)

        val task = repository.addTask(Task(
            title = title,
            description = description,
            priority = priority
        ))

        clearUi()

        taskAddedListener?.onTaskAdded(task)
        dismiss()
    }

    private fun clearUi() {
        newTaskTitleInput.text.clear()
        newTaskDescriptionInput.text.clear()
        prioritySelector.setSelection(currentPriority)
    }

    private fun isInputEmpty() = isEmpty(newTaskTitleInput.text) || isEmpty(newTaskDescriptionInput.text)

    companion object {
        fun newInstance(): AddTaskFragmentDialog {
            return AddTaskFragmentDialog()
        }
    }
}