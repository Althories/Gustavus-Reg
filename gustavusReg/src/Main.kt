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