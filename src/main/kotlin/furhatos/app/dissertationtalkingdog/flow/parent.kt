package furhatos.app.dissertationtalkingdog.flow


import furhatos.flow.kotlin.*
import furhatos.app.dissertationtalkingdog.flow.main.Sleeping

val Parent: State = state {

    onUserEnter(instant = true) {
        when { // "it" is the user that entered
            furhat.isAttendingUser -> furhat.glance(it) // Glance at new users entering
            !furhat.isAttendingUser -> furhat.attend(it) // Attend user if not attending anyone
        }
    }

    onUserLeave(instant = true) {
        when {
            !users.hasAny() -> { // last user left
                furhat.attendNobody()
                goto(Sleeping)
            }
            furhat.isAttending(it) -> furhat.attend(users.other) // current user left
            !furhat.isAttending(it) -> furhat.glance(it.head.location) // other user left, just glance
        }
    }

}
/**
val Parent: State = state {
    onButton("SLEEP", color = Color.Blue) {
        goto(Sleep)
    }
    onButton("WAKE", color = Color.Blue) {
        goto(WakeUp)
    }
    onButton("SHOW ALL GESTURES", color = Color.Blue) {
        goto(ShowAllGestures)
    }
}
*/