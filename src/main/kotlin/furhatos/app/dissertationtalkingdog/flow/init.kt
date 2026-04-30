package furhatos.app.dissertationtalkingdog.flow

import furhatos.app.dissertationtalkingdog.extensions.setDogCharacter
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
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)
        setDogCharacter()
    }

    onEntry {
        furhat.attendNobody()

        // Give the robot time to detect the user
        delay(500)

        when (users.count) {
            0 -> goto(Sleeping)
            1 -> {
                furhat.attend(users.random)
                goto(Greeting)
            }
            else -> {
                furhat.attendAll()
                goto(Greeting)
            }
        }
    }

}


