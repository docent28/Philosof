fun main() {
    println("Укажите числом количество философов за круглым столом")
    val countPhilosopher = enteringCountNum()

    val philosophers = ArrayList<Philosopher>()
    val forks = ArrayList<Fork>()

    println("Введите имена философов")
    for (i in 0 until countPhilosopher) {
        print("Имя философа номер $i - ")
        val namePhilosopher = readln()
        philosophers.add(Philosopher(namePhilosopher))
        forks.add(Fork("fork$i"))
    }

    val timeMap: MutableMap<Int, String> =
        philosophers.associateBy(keySelector = { philosophers.indexOf(it) },
            valueTransform = { it.name }
        ) as MutableMap<Int, String>

    while (timeMap.isNotEmpty()) {
        val indexPhilosopher = selectPhilosopherRandom(timeMap)
        if (checkForkOccupancy(forks, indexPhilosopher, countPhilosopher)) {
            philosophers[indexPhilosopher].state = "takes Food"
            println("${philosophers[indexPhilosopher].name} взял вилку слева и справа от себя ")
        } else {
            println("${philosophers[indexPhilosopher].name} размышляет")
        }
        timeMap.remove(indexPhilosopher)
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

fun checkForkOccupancy(timeForks: ArrayList<Fork>, indexPhilosopher: Int, countPhilosopher: Int): Boolean {
    val indexForkLeft = indexPhilosopher
    val indexForkRight = if (indexPhilosopher - 1 == -1) {
        countPhilosopher - 1
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

fun selectPhilosopherRandom(mapPhilosopher: Map<Int, String>): Int {
    return ArrayList(mapPhilosopher.keys).random()
}