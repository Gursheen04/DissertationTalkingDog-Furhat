package furhatos.app.dissertationtalkingdog.flow.main

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Thanks
import furhatos.records.Location
import furhatos.app.dissertationtalkingdog.flow.DogMode
import furhatos.app.dissertationtalkingdog.flow.Parent
import furhatos.app.dissertationtalkingdog.flow.dogMode
import furhatos.app.dissertationtalkingdog.gestures.*
import furhatos.app.dissertationtalkingdog.utils.Transcript
import furhatos.app.dissertationtalkingdog.gestures.panting1





val Ending: State = state(Parent) {
    onEntry {
        Transcript.log("STATE", "Ending")

        if (dogMode == DogMode.FULL){
            // Friendly closing gesture
            furhat.gesture(backchannelSmile())
            furhat.gesture(panting1)

            val line = "Thank you. I’m done now."
            furhat.say(line)
            Transcript.log("ROBOT", line)
        } else {
            val line = "Thank you. I’m done now."
            furhat.say(line)
            Transcript.log("ROBOT", line)
        }

        // Dog looks down to signal disengagement
        furhat.attend(Location.DOWN)

        // Begin listening in case user says "thanks"
        furhat.listen()
    }

    onResponse<Thanks> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "Thanks")

        if (dogMode == DogMode.FULL){
            // Soft, friendly dog reaction
            furhat.gesture(backchannelSmile())
            furhat.gesture(panting1)
            val line = "Goodbye."
            furhat.say(line)
            Transcript.log("ROBOT", line)
        } else {
            val line = "Goodbye."
            furhat.say(line)
            Transcript.log("ROBOT", line)
        }
    }


    onResponse {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "None")

        if (dogMode == DogMode.FULL){
            // Generic soft disengagement
            furhat.gesture(smallNod())
            furhat.gesture(panting1)

            val line = "Goodbye."
            furhat.say(line)
            Transcript.log("ROBOT", line)
        } else {
            val line = "Goodbye."
            furhat.say(line)
            Transcript.log("ROBOT", line)
        }
    }

    /** after 15 seconds, user expected to leave interaction and another user to stat a new convo with the robot */
    onTime(15000) {
        Transcript.log("SYSTEM", "Ending timeout → Sleeping")
        goto(Sleeping)
    }
}