package dvranjicic.taskie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dvranjicic.taskie.R
import dvranjicic.taskie.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class SwipeToDeleteAdapter(private val items: MutableList<Task>) : RecyclerView.Adapter<SwipeToDeleteAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(parent)

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    class VH(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_tasks, parent, false)) {

        fun bind(name: Task) = with(itemView) {
            taskTitle.text = name.title
        }
    }
}