package furhatos.app.dissertationtalkingdog.nlu


import furhatos.nlu.Intent
import furhatos.util.Language

class WeatherIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "it's raining",
        "rain", "rainy", "heavy rain",
        "wet weather",
        "cloudy",
        "grey sky",
        "gloomy",
        "stormy",
        "wet ground"
    )
}


class ParkIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "park", "benches",
        "trees", "puddles",
        "wet ground",
        "in the park",
        "there are puddles",
        "lots of trees"
    )
}

class BoyIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "boy", "child", "kid",
        "he is wearing a red raincoat",
        "red jacket",
        "red raincoat",
        "boots",
        "rain boots",
        "the boy looks happy"
    )
}

class DogIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "dog", "the dog", "wet dog",
        "about the dog",
        "something about the dog",
        "sad dog", "the dog looks unhappy",
        "the dog is being walked",
        "walk",
        "walking the dog", "the dog seems sad",
        "the dog looks sad", "the dog appears sad",
        "the dog looks unhappy",
        "the dog seems unhappy"
    )
}

class ObjectIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "umbrella", "leash",
        "lead", "walking stick",
        "raincoat",
        "boots",
        "rain boots"
    )
}

class StopIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "nothing more",
        "that's all",
        "i'm done",
        "finished",
        "i have nothing else",
        "no more to describe"
    )
}

class EmotionIntent : Intent() {
    override fun getExamples(lang: Language) = listOf(
        "sad", "gloomy", "upset",
        "happy", "excited", "joyful",
        "angry", "mad", "furious",
        "scared", "afraid", "nervous"
    )
}