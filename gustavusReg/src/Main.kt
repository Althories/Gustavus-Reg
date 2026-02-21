//Main function and Data Globals: Nikolas Kopek

import java.util.Objects

//Data globals as defined in UML diagram
object GlobalData {
    // Maps object IDs to their actual objects
    var users : MutableMap<Int, User> = mutableMapOf() //Handles Admin, Student, Professor children
    var courses : MutableMap<Int, Course> = mutableMapOf()
    var degrees : MutableMap<Int, Degree> = mutableMapOf()
    var departments : MutableMap<Int, Department> = mutableMapOf()
}

fun main() {
    var a = User(0)
    var b = Admin(1)
    //var c = Professor(2, intArrayOf(1, 2, 3, 4), "Olin")
    println(b)
    //println(c)
}