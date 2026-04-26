package furhatos.app.dissertationtalkingdog.flow.main

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state
import furhat.libraries.standard.GesturesLib

val Sleeping: State = state {
    /** attend nobody, and wait for user to enter and attend and then go to greteing once user enter */
    onEntry {
        furhat.gesture(GesturesLib.PerformFallAsleepPersist)
    }

    /** then go to greeting which will start the actual conversation. */

    onUserEnter {
        furhat.gesture(GesturesLib.PerformWakeUpWithHeadShake)
        furhat.attend(it)
        goto(Greeting)
    }
}