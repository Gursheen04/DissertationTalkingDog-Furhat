package furhatos.app.dissertationtalkingdog

import furhatos.skills.Skill
import furhatos.app.dissertationtalkingdog.flow.Init
import furhatos.flow.kotlin.Flow


fun main(args: Array<String>) {
    println("ABSOLUTE PATH TEST = " + java.io.File("skill.properties").absolutePath + " | exists = " + java.io.File("skill.properties").exists())

    Skill.main(args)
}


