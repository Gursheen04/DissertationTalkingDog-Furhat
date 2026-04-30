package furhatos.app.dissertationtalkingdog

import furhatos.app.dissertationtalkingdog.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class DissertationtalkingdogSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}