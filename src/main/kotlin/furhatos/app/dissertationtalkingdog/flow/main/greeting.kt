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
import furhatos.app.dissertationtalkingdog.flow.Parent


val Greeting: State = state (Parent) {
    onEntry {
        //furhat.attend(users.current)
        furhat.say {
            random {
                +"Hello! It is nice to have some company today."
                +"Hello there! It is nice to see you."
                +"Hi there! It is so nice to see you."
            }
            +GesturesLib.PerformSmile1 /** Multimodal interaction ith speech and gestures */
        }
        furhat.listen()
    }

    // User returns greeting (hi, hello, hey)
    onResponse<Greeting> {
        furhat.ask {
            +"I am furry. I am a robot dog."
            +GesturesLib.PerformSmile1
            furhat.ask("Would you like to describe the park scene to me?")
            furhat.listen()
        }
    }
    /**

    // User says their name
    onResponse<PersonName> {
    val name = it.text
    furhat.say("Nice to meet you, $name.")
    furhat.ask("Would you like to describe the park scene to me?")
    furhat.listen()
    }
     */

    //User agrees to describe the picture
    onResponse<Yes> {
        furhat.say("Great!")
        goto(Description)
    }

    // User says no
    onResponse<No> {
        furhat.say("No worries. Maybe another time.")
        goto(Sleeping)
    }

    // USER SAYS NOTHING
    onNoResponse {
        furhat.say("Sorry, I didn't catch that.")
        furhat.listen()
    }

    // ANY OTHER RANDOM RESPONSE
    onResponse {
        furhat.say("Sorry, I didn’t quite get that.")
        furhat.listen()
    }

    /** ------------------------------------------------
    /** user returned the greeting Hi */
    onResponse {
    var canIAskYouSomething = furhat.askYN("Can I ask you soemthing?")
    if (canIAskYouSomething) {
    goto(Description)
    } else {
    furhat.say("Sorry to bother you")
    goto(Sleeping)
    }
    }

    /** user did not return the greeting */
    onNoResponse {
    raise(Greeting())
    }

    /** user said soemthing something else and the furhat continues */
    onResponse {
    furhat.say("anyway...")
    raise(Greeting())
    }
    -------------------------------------------------------*/

}

