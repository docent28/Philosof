class Philosopher(val name: String) {
    var state = "reflects"
    fun takesFork() {
        this.state = "tacesFood"
    }
}