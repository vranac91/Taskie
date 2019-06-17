package dvranjicic.taskie.common

import androidx.room.TypeConverter
import dvranjicic.taskie.R
import dvranjicic.taskie.model.Priority

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromPriority(value: Priority): Int {
            return value.getColor()
        }

        @TypeConverter
        @JvmStatic
        fun toPriority(value: Int): Priority {
            return when(value) {
                R.color.colorLow -> Priority.LOW
                R.color.colorMedium -> Priority.MEDIUM
                else -> Priority.HIGH
            }
        }
    }
}