package furhatos.app.dissertationtalkingdog.flow.main

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.behavior
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Thanks
import furhatos.records.Location
import furhat.libraries.standard.GesturesLib
import furhatos.app.dissertationtalkingdog.flow.Parent
import gestures.*
import furhatos.app.dissertationtalkingdog.gestures.*
import furhatos.app.dissertationtalkingdog.utils.Transcript


val Ending: State = state(Parent) {
    onEntry {
        Transcript.log("STATE", "Ending")

        // Friendly closing gesture
        furhat.gesture(backchannelSmile())
        furhat.gesture(panting1)

        val line = "Thank you. I’m done now."
        furhat.say(line)
        Transcript.log("ROBOT", line)

        // Dog looks down to signal disengagement
        furhat.attend(Location.DOWN)

        // Begin listening in case user says "thanks"
        furhat.listen()
    }

    onResponse<Thanks> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "Thanks")

        // Soft, friendly dog reaction
        furhat.gesture(backchannelSmile())
        furhat.gesture(panting1)

        val line = "Goodbye."
        furhat.say(line)
        Transcript.log("ROBOT", line)
    }
    onResponse {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "None")

        // Generic soft disengagement
        furhat.gesture(smallNod())
        furhat.gesture(panting1)

        val line = "Goodbye."
        furhat.say(line)
        Transcript.log("ROBOT", line)
    }

    /** after 15 seconds, user expected to leave interaction and another user to stat a new convo with the robot */
    onTime(15000) {
        Transcript.log("SYSTEM", "Ending timeout → Sleeping")
        goto(Sleeping)
    }
}