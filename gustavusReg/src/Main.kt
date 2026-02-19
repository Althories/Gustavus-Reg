//Main function and Data Globals: Nikolas Kopek

import java.util.Objects

//Data globals
object GlobalData {
    var users : MutableList<User> = mutableListOf() //Handles Admin, Student, Professor children
    var courses : MutableList<Course> = mutableListOf()
    var degrees : MutableList<Degree> = mutableListOf()
    var departments : MutableList<Department> = mutableListOf()
}

fun main() {
    var a = User(0)
    var b = Admin(1)
    //var c = Professor(2, intArrayOf(1, 2, 3, 4), "Olin")
    println(a)
    println(b)
    //println(c)
}