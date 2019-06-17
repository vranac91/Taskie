package dvranjicic.taskie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    var taskDbId: Long? = null,
    val title: String,
    val description: String,
    val priority: Priority
) {

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false // null check
        if (javaClass != other.javaClass)
            return false // type check

        val mOther = other as Task
        return taskDbId == mOther.taskDbId
                && title == mOther.title
                && description == mOther.description
                && priority == mOther.priority
    }

    override fun hashCode(): Int {
        return Objects.hash(title, description, priority)
    }
}
