package com.nerdery.jvm

/**
 * Created by johuff on 10/30/16.
 */
enum class ScalePattern(val pattern: List<Int>) {
    MAJOR_SCALE(listOf(2, 2, 1, 2, 2, 2, 1)),
    NATURAL_MINOR_SCALE(listOf(2, 1, 2, 2, 1, 2, 2)),
    HARMONIC_MINOR_SCALE(listOf(2, 1, 2, 2, 1, 3, 1))
}