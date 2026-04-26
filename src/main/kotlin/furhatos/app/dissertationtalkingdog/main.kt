package furhatos.app.dissertationtalkingdog

import furhatos.skills.Skill
import furhatos.app.dissertationtalkingdog.flow.Init
import furhatos.flow.kotlin.Flow

class DissertationtalkingdogSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
