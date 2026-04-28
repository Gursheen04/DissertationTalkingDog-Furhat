package furhatos.app.dissertationtalkingdog.extensions

import furhatos.app.dissertationtalkingdog.dog.CurrentState
import furhatos.app.dissertationtalkingdog.dog.Dog
import furhatos.app.dissertationtalkingdog.flow.MissingMask
import furhatos.app.dissertationtalkingdog.flow.logger
import furhatos.app.dissertationtalkingdog.gestures.*
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.RandomPolicy
import furhatos.flow.kotlin.furhat
import furhatos.app.dissertationtalkingdog.gestures.WakeUpWithHeadShake
import org.apache.logging.log4j.Level

fun FlowControlRunner.setDogCharacter() {
    if (furhat.faces.get("pug") != null) {
        furhat.setModel("pug", "default")
    } else if (furhat.faces.get("dog") != null) {
        furhat.setModel("dog", "default")
    } else {
        logger.log(Level.WARN, "Missing dog mask!")
        furhat.say {
            +"This skill requires that you have the dog mask and the dog 3D model. "
            +"So you have to fix that, before I can take the shape of the dog called Lucky. "
            +delay(400)
            +"Go to the home tab, in the web console, to see what mask models are currently available for me. "
        }
        goto(MissingMask)
    }
}

fun FlowControlRunner.randomBark() {
    random(
            { furhat.gesture(bark1, async = false) },
            { furhat.gesture(bark2, async = false) },
            { furhat.gesture(bark3, async = false) },
            policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT
    )
}

fun FlowControlRunner.randomWhimpering() {
    random({
        furhat.gesture(whimpering1, async = false)
    }, {
        furhat.gesture(whimpering2, async = false)
    }, {
        furhat.gesture(whimpering3, async = false)
    }, {
        furhat.gesture(whimpering5, async = false)
    }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)
}

fun FlowControlRunner.randomGrowl() {
    random({
        furhat.gesture(growl2, async = false)
    }, {
        furhat.gesture(growl4, async = false)
    }, {

    }, {

    }, {

    }, {

    }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)
}

fun FlowControlRunner.awakeIfAsleep(userId: String) {
    when (Dog.currentState) {
        CurrentState.AWAKE -> {
            furhat.attend(userId)
        }
        CurrentState.ASLEEP -> {
            furhat.gesture(WakeUpWithHeadShake)
            delay(500)
            furhat.attend(userId)
        }
    }
}