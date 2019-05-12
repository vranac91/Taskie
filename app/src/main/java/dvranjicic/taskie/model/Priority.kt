package dvranjicic.taskie.model

import androidx.annotation.ColorRes
import dvranjicic.taskie.R

enum class Priority(@ColorRes private val colorRes: Int) {
    LOW(R.color.colorLow),
    MEDIUM(R.color.colorMedium),
    HIGH(R.color.colorHigh);

    fun getColor(): Int = colorRes
}