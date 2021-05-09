package com.example.diffutil.util

import android.graphics.Color
import kotlin.random.Random

object RandomColorGenerator {

    private const val RED = Color.RED
    private const val BLACK = Color.BLACK
    private const val BLUE = Color.BLUE
    private const val CYAN = Color.CYAN
    private const val GREEN = Color.GREEN
    private const val MAGENTA = Color.MAGENTA
    private const val YELLOW = Color.YELLOW
    private const val GRAY = Color.GRAY

    private val colors = arrayOf(
        RED,
        BLACK,
        BLUE,
        CYAN,
        GREEN,
        MAGENTA,
        YELLOW,
        GRAY
    )

    fun getRandomColor(): Int {
        return colors[Random.nextInt(colors.size)]
    }
}