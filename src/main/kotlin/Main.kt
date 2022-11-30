fun main() {
    println("Укажите числом количество философов за круглым столом")
    val countPhilosopher = enteringCountNum()

    val philosopherJohn = Philosopher("John")
    val philosopherHarry = Philosopher("Harry")
    val philosopherJack = Philosopher("Jack")
    val philosopherRonald = Philosopher("Ronald")
    val philosopherRichard = Philosopher("Richard")

    val forkOne = Fork("fork01")
    val forkTwo = Fork("fork02")
    val forkThree = Fork("fork03")
    val forkFour = Fork("fork04")
    val forkFive = Fork("fork05")

    val philosophers =
        arrayOf(philosopherJohn, philosopherHarry, philosopherJack, philosopherRonald, philosopherRichard)
    val forks = arrayOf(forkOne, forkTwo, forkThree, forkFour, forkFive)

    val testForks = ArrayList<Fork>(5)
    for (i in 1..5) {
        testForks.add(Fork("fork$i"))
    }
    for (ind in 0..4) {
        println("[${testForks[ind].name}]")
    }

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

fun enteringCountNum(): Int {
    var intCountBool = true
    var userCount = ""
    while (intCountBool) {
        print("Введите количество с помощью положительного целого числа - ")
        userCount = readln()
        if (isPosOrNegNumber(userCount)) {
            intCountBool = false
        }
    }
    return userCount.toInt()
}

fun isPosOrNegNumber(s: String?): Boolean {
    val regex = """^[0-9]+$""".toRegex()
    return if (s.isNullOrEmpty()) false
    else regex.matches(s)
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