import kotlin.random.Random

fun main(args: Array<String>) {
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

    var timeMap: MutableMap<Int, String> =
        philosophers.associateBy(keySelector = { philosophers.indexOf(it) },
            valueTransform = { it.name }
        ) as MutableMap<Int, String>

    println(selectPhilosopher(timeMap, forks, philosophers))


//    println("${philosophers[indexPhilosopher].name} взял вилку слева и справа от себя ")

//    forks.forEach { println(it.state) }

//    forks.forEachIndexed { ind, el -> println("index = $ind name = ${el.state}") }

//    println(timeMap)

//    timeMap.remove(indexPhilosopher)
    val iii = Random.nextInt(timeMap.size)
}

fun selectPhilosopher(
    mapPhilosopher: Map<Int, String>,
    timeForks: Array<Fork>,
    timePhilosopher: Array<Philosopher>
): Int {

    val valRandom = Random.nextInt(mapPhilosopher.size)
    val indexPhilosopher = mapPhilosopher.keys

    val indexForkLeft = indexPhilosopher
    val indexForkRight = if (indexPhilosopher - 1 == -1) {
        4
    } else {
        indexPhilosopher - 1
    }
    timeForks[indexForkLeft].state = "busy"
    timeForks[indexForkRight].state = "busy"
    timePhilosopher[indexPhilosopher].state = "takes Food"
    return 6
}