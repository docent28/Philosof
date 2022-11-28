fun main() {
    val philosopherJohn = Philosopher("John")
    val philosopherHarry = Philosopher("Harry")
    val philosopherJack = Philosopher("Jack")
    val philosopherRonald = Philosopher("Ronald")
    val philosopherRichard = Philosopher("Richard")

    val forkOne = Fork(1)
    val forkTwo = Fork(2)
    val forkThree = Fork(3)
    val forkFour = Fork(4)
    val forkFive = Fork(5)

    val philosophers =
        arrayOf(philosopherJohn, philosopherHarry, philosopherJack, philosopherRonald, philosopherRichard)
    val forks = arrayOf(forkOne, forkTwo, forkThree, forkFour, forkFive)

    val timeMap: MutableMap<Int, String> =
        philosophers.associateBy(keySelector = { philosophers.indexOf(it) },
            valueTransform = { it.name }
        ) as MutableMap<Int, String>

    while (timeMap.isNotEmpty()) {
        val indexPhilosopher = selectPhilosopher(timeMap)
        if (checkForkOccupancy(forks, indexPhilosopher)) {
            philosophers[indexPhilosopher].state = "takes Food"
            println("${philosophers[indexPhilosopher].name} взял вилку слева и справа от себя ")
            timeMap.remove(indexPhilosopher)
        } else {
            println("${philosophers[indexPhilosopher].name} размышляет")
            timeMap.remove(indexPhilosopher)
        }
    }
}

fun checkForkOccupancy(timeForks: Array<Fork>, indexPhilosopher: Int): Boolean {
    val indexForkLeft = indexPhilosopher
    val indexForkRight = if (indexPhilosopher - 1 == -1) {
        4
    } else {
        indexPhilosopher - 1
    }
    return if (timeForks[indexForkLeft].state == "busy" || timeForks[indexForkRight].state == "busy") {
        false
    } else {
        timeForks[indexForkLeft].state = "busy"
        timeForks[indexForkRight].state = "busy"
        true
    }
}

fun selectPhilosopher(mapPhilosopher: Map<Int, String>): Int {
    return ArrayList(mapPhilosopher.keys).random()
}