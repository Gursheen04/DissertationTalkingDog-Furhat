package furhatos.app.dissertationtalkingdog.flow

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.flow.kotlin.voice.Voice
import furhatos.app.dissertationtalkingdog.flow.main.Sleeping
import furhatos.app.dissertationtalkingdog.flow.main.Greeting
import furhatos.app.dissertationtalkingdog.settings.DISTANCE_TO_ENGAGE
import furhatos.app.dissertationtalkingdog.settings.MAX_NUMBER_OF_USERS


val Init: State = state {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)

        /** set character parameters */
        furhat.mask = "dog"
        furhat.voice = Voice(name = "Matthew")
        furhat.character = "Alex"

        /** start interaction */
        /** Init is triggered the first time you enter this state and never again */
        when (users.count) {
            0 -> goto(Sleeping) /** wait for users to attend */
            1 -> {
                furhat.attend(users.random) /** attend that user and go to greetings*/
                goto(Greeting)
            }
            else -> {
                furhat.attendAll() /**for more than one users, make sure to attend all and go to greetings.*/
                goto(Greeting)
            }

        }
    }
}