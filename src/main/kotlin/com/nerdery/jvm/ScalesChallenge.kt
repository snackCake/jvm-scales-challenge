package com.nerdery.jvm

import javax.sound.midi.MidiSystem
import javax.sound.midi.Sequence

/**
 * @author Josh Klun (jklun@nerdery.com)
 */
class ScalesChallenge {
    /*
        This function takes a Note and returns the relative notes for the Major scale.
        Note - this function was left in so that it is backwards compatible with any code that uses this signature.
     */
    fun buildScale(note: Note): List<RelativeNote> {
        return buildScale(note, ScalePattern.MAJOR_SCALE)
    }

    /*
        Builds the scale of relative notes based on the root Note and ScalePattern passed in.
     */
    fun buildScale(note: Note, pattern: ScalePattern): List<RelativeNote> {
        val cc: ChromaticCircle = ChromaticCircle().getInstance()
        return cc.buildScale(note, pattern)
    }

    fun convertToMidi(notes: List<RelativeNote>): Sequence = NotesMidiGenerator(notes).generateSong()

    fun playMidi(sequence: Sequence): Unit {
        val sequencer = MidiSystem.getSequencer()
        sequencer.sequence = sequence
        sequencer.open()
        Thread.sleep(300L)
        sequencer.start()
        Thread.sleep(10000L)
        sequencer.stop()
        sequencer.close()
    }
}

val usageMessage = "Enter a key signature: C_FLAT, C, C_SHARP, etc\nand a scale: MAJOR_SCALE (default), NATURAL_MINOR_SCALE, HARMONIC_MINOR_SCALE"

fun main(args: Array<String>) = println(when (args.size) {
    0 -> usageMessage
    else -> try {
        val challenge = ScalesChallenge()

        var scalePattern: ScalePattern = ScalePattern.MAJOR_SCALE
        if (args.size == 2) {
            scalePattern = ScalePattern.valueOf(args[1])
        }

        val scale = challenge.buildScale(Note.valueOf(args.first()), scalePattern)

        val sequence = challenge.convertToMidi(scale)
        challenge.playMidi(sequence)
        scale.joinToString()
    } catch (e: IllegalArgumentException) {
        usageMessage
    }
})
