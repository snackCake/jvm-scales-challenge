package com.nerdery.jvm

import java.util.*

/**
 * Created by johuff on 10/30/16.
 */
class ChromaticCircle {

    val circle : List<Note> = listOf(
            Note.A,
            Note.A_SHARP,
            Note.B,
            Note.C,
            Note.C_SHARP,
            Note.D,
            Note.D_SHARP,
            Note.E,
            Note.F,
            Note.F_SHARP,
            Note.G,
            Note.G_SHARP
    )

    fun getInstance(): ChromaticCircle {
        return this
    }

    fun buildScale(root: Note, pattern: ScalePattern): List<RelativeNote> {
        println(root.name + "   " + pattern.name)

        val scale: ArrayList<RelativeNote> = ArrayList<RelativeNote>()

        var octave: Int = 0
        var index: Int = circle.indexOf(root)

        scale.add(RelativeNote(root, octave))
        print(root.name + " - ")

        for (step: Int in pattern.pattern) {
            index += step

            if (index > 11) {
                index -= 12
                octave++
            }
            val note: Note = circle.get(index)
            scale.add(RelativeNote(note, octave))
            print(note.name + " - ")
        }
        println("")
        return scale
    }
}