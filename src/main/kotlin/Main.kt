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

    val fastest = philosophers.random()

    println(fastest.name)
}