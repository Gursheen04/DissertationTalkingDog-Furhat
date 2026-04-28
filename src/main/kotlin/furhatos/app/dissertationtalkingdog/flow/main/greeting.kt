package furhatos.app.dissertationtalkingdog.flow.main

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Greeting
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhat.libraries.standard.GesturesLib
import furhatos.app.dissertationtalkingdog.flow.DogMode
import furhatos.app.dissertationtalkingdog.flow.Parent
import furhatos.app.dissertationtalkingdog.flow.dogMode
import gestures.*
import furhatos.app.dissertationtalkingdog.gestures.*
import furhatos.app.dissertationtalkingdog.utils.Transcript


val Greeting: State = state (Parent) {
    onEntry {
        if (dogMode == DogMode.FULL){
            // Dog greeting gestures
            furhat.gesture(backchannelSmile())
            furhat.gesture(panting1)
            furhat.gesture(slightlySurprised())

            val greetingLine = listOf(
                "Hello! It is nice to have some company today.",
                "Hello there! It is nice to see you.",
                "Hi there! It is so nice to see you."
            ).random()

            furhat.say(greetingLine)
            Transcript.log("ROBOT", greetingLine)
            Transcript.log("STATE", "Greeting")
        } else {
            furhat.say("Hello, nice to meet you")
            Transcript.log("ROBOT", "Hello, nice to meet you")
            Transcript.log("STATE", "Greeting")
        }
        furhat.listen()
    }

    // User returns greeting (hi, hello, hey)
    onResponse<Greeting> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "Greeting")

        if (dogMode == DogMode.FULL){
            furhat.gesture(doubleNod())
            furhat.gesture(bark1)

            furhat.say("I am Furry. I am a robot dog.")
            Transcript.log("ROBOT", "I am Furry. I am a robot dog.")

            furhat.say("Would you like to describe the park scene to me?")
            Transcript.log("ROBOT", "Would you like to describe the park scene to me?")
        } else {
            furhat.say("I am Furry, a robot dog.")
            Transcript.log("ROBOT", "I am Furry, a robot dog.")

            furhat.say("Would you like to describe the park scene to me?")
            Transcript.log("ROBOT", "Would you like to describe the park scene to me?")
        }

        furhat.listen()
    }

    //User agrees to describe the picture
    onResponse<Yes> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "Yes")

        if (dogMode == DogMode.FULL){
            furhat.gesture(smallNod())
            furhat.gesture(panting1)

            furhat.say("Great.")
            Transcript.log("ROBOT", "Great.")
        } else {
            furhat.say("Great.")
            Transcript.log("ROBOT", "Great.")
        }
        goto(Description)
    }

    // User says no
    onResponse<No> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "No")

        if (dogMode == DogMode.FULL){
            furhat.gesture(slightlyThoughtful())
            furhat.gesture(whimpering1)

            furhat.say("Okay.")
            Transcript.log("ROBOT", "Okay.")
        } else {
            furhat.say("Okay.")
            Transcript.log("ROBOT", "Okay.")
        }
        goto(Sleeping)
    }

    // USER SAYS NOTHING
    onNoResponse {
        Transcript.log("USER", "<silence>")
        Transcript.log("INTENT", "None")

        if (dogMode == DogMode.FULL){
            furhat.gesture(slightlyThoughtful())
            furhat.gesture(panting1)

            furhat.say("Sorry, I didn’t get that.")
            Transcript.log("ROBOT", "Sorry, I didn’t get that.")
        } else {
            furhat.say("Sorry, I didn’t get that.")
            Transcript.log("ROBOT", "Sorry, I didn’t get that.")
        }
        furhat.listen()
    }

    // ANY OTHER RANDOM RESPONSE
    onResponse {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "None")

        if (dogMode == DogMode.FULL){
            furhat.gesture(smallNod())

            furhat.say("Are you there?")
            Transcript.log("ROBOT", "Are you there?")
        } else {
            furhat.gesture(smallNod())

            furhat.say("Are you there?")
            Transcript.log("ROBOT", "Are you there?")
        }
        furhat.listen()
    }
}

