import java.util.Objects

//Main function and Data Globals: Nikolas Kopek

//Data globals
object GlobalData {
    var users : MutableList<Objects> = mutableListOf()
    var courses : MutableList<Objects> = mutableListOf()
    var degrees : MutableList<Objects> = mutableListOf()
    var departments : MutableList<Objects> = mutableListOf()
}

fun main() {
    var a = User(0)
    var b = Admin(1)
    //var c = Professor(2, intArrayOf(1, 2, 3, 4), "Olin")
    println(a)
    println(b)
    //println(c)
}