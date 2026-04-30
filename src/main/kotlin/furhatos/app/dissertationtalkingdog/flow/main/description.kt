package furhatos.app.dissertationtalkingdog.flow.main

import furhatos.app.dissertationtalkingdog.flow.DogMode
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.app.dissertationtalkingdog.flow.Parent
import furhatos.app.dissertationtalkingdog.flow.dogMode
import furhatos.app.dissertationtalkingdog.utils.Transcript
import furhatos.app.dissertationtalkingdog.nlu.ParkIntent
import furhatos.app.dissertationtalkingdog.nlu.StopIntent
import furhatos.app.dissertationtalkingdog.nlu.BoyIntent
import furhatos.app.dissertationtalkingdog.nlu.ObjectIntent
import furhatos.app.dissertationtalkingdog.nlu.WeatherIntent
import furhatos.app.dissertationtalkingdog.nlu.DogIntent
import furhatos.app.dissertationtalkingdog.nlu.EmotionIntent
import furhatos.app.dissertationtalkingdog.gestures.*



/** this file is made with questions that the furhat is gonna ask the user */
val Description: State = state(Parent) {

    //var timer: Job? = null
    var timerRunning = true

    onEntry {
        // Start the 90 second timer in background
        timerRunning = true
        Transcript.log("SYSTEM", "Entered Description state")

        parallel {
            delay(90000)
            if (timerRunning) {
                furhat.say("Thank you for your description")
                Transcript.log("ROBOT", "Thank you for your description")
                goto(Ending)
            }
        }

        if (dogMode == DogMode.FULL){
            furhat.gesture(bark1)
            delay(400)
            furhat.say("Please begin describing the picture.")
            Transcript.log("ROBOT", "Please begin describing the picture.")
        } else {
            furhat.say("Please begin describing the picture.")
            Transcript.log("ROBOT", "Please begin describing the picture.")
        }

        reentry()
    }

    onReentry {
        delay(200)
        furhat.listen()
    }

    // --- STOP INTENT - calm ending ---
    onResponse<StopIntent> {
        timerRunning = false
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "StopIntent")

        if (dogMode == DogMode.FULL){
            // Soft, friendly dog acknowledgement
            furhat.gesture(backchannelSmile())
            furhat.gesture(panting1)

            furhat.say("Thank you for describing the picture to me.")
            Transcript.log("ROBOT", "Thank you for describing the picture to me.")
        } else {
            furhat.say("Thank you for describing the picture to me.")
            Transcript.log("ROBOT", "Thank you for describing the picture to me.")
        }
        goto(Ending)
    }

    // --- WEATHER ---
    onResponse<WeatherIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "WeatherIntent")

        if (dogMode == DogMode.FULL){
            furhat.gesture(slightlySurprised())
            furhat.gesture(whimpering1)
        }
        reentry()
    }

    // --- PARK ---
    onResponse<ParkIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "ParkIntent")

        if (dogMode == DogMode.FULL){
            furhat.gesture(slightlySurprised())
            furhat.gesture(sniffing1)
            furhat.gesture(bark1)
        }
        reentry()
    }

    // --- BOY ---
    onResponse<BoyIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "BoyIntent")

        if (dogMode == DogMode.FULL){
            furhat.gesture(slightlyThoughtful())
            furhat.gesture(sniffing3)
            furhat.gesture(panting1)
        }
        reentry()
    }

    // --- DOG ---
    onResponse<DogIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "DogIntent")

        if (dogMode == DogMode.FULL){
            furhat.gesture(doubleNod())
            furhat.gesture(bark1)
        }
        reentry()
    }

    // --- OBJECTS ---
    onResponse<ObjectIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "ObjectIntent")

        if(dogMode == DogMode.FULL) {
            furhat.gesture(smallNod())
            furhat.gesture(whimpering1)
        }
        reentry()
    }

    onResponse<EmotionIntent> {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "EmotionIntent")

        if (dogMode == DogMode.FULL) {
            val text = it.text.lowercase()
            when {
                "sad" in text || "gloomy" in text || "upset" in text -> {
                    furhat.gesture(slightlyThoughtful())
                    furhat.gesture(whimpering2)
                }
                "happy" in text || "joyful" in text -> {
                    furhat.gesture(doubleNod())
                    furhat.gesture(bark2)
                }
                "excited" in text -> {
                    furhat.gesture(backchannelSmile())
                    furhat.gesture(bark3)
                }
                "scared" in text || "afraid" in text || "nervous" in text -> {
                    furhat.gesture(slightlySurprised())
                    furhat.gesture(whimpering1)
                }
                "angry" in text || "mad" in text || "furious" in text -> {
                    furhat.gesture(slightlyThoughtful())
                    furhat.gesture(growl2)
                }
            }
        }

        reentry()
    }

    // --- ANY OTHER SPEECH that doesn't match the intent - soft engagement---
    onResponse {
        Transcript.log("USER", it.text)
        Transcript.log("INTENT", "None")

        if (dogMode == DogMode.FULL){
            furhat.gesture(backchannelSmile())
            furhat.gesture(bark1)
        }
        reentry()
    }


    // --- SILENCE ---
    onNoResponse {
        if (dogMode == DogMode.FULL){
            furhat.gesture(smallNod())
            reentry()
        }
        if (dogMode == DogMode.HALF) {
            furhat.say("Okay.")
            reentry()
        }
    }

    onExit {
        timerRunning = false
        Transcript.log("SYSTEM", "Exiting Description state")
    }
}