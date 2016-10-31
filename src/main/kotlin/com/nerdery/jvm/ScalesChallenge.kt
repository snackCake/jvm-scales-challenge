package com.nerdery.jvm

import javax.sound.midi.MidiSystem
import javax.sound.midi.Sequence

/**
 * @author Josh Klun (jklun@nerdery.com)
 */
class ScalesChallenge {
    fun buildScale(note: Note): List<RelativeNote> {
        return buildScale(note, ScalePattern.MAJOR_SCALE)
    }

    fun buildScale(note: Note, pattern: ScalePattern): List<RelativeNote> {
        var cc : ChromaticCircle = ChromaticCircle().getInstance()
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

val usageMessage = "Enter a key signature. For example: C_FLAT, C, C_SHARP"

fun main(args: Array<String>) = println(when (args.size) {
    0 -> usageMessage
    else -> try {
        val challenge = ScalesChallenge()
        //val scale = challenge.buildScale(Note.valueOf(args.first()))
        val scale = challenge.buildScale(Note.valueOf("C"), ScalePattern.HARMONIC_MINOR_SCALE)

        val sequence = challenge.convertToMidi(scale)
        challenge.playMidi(sequence)
        scale.joinToString()
    } catch (e: IllegalArgumentException) {
        usageMessage
    }
})
