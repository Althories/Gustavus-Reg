// Branden Hopper (Reedspun)

class Professor(userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set", var currentCourses: MutableList<Int>, var officeLocation: String): User(userId) {

    // Updates a course's description.
    // Course must exist and be in this professor's current courses.
    // Returns true on success, throws error otherwise.
    fun updateCourseDescription(courseId: Int, newDescription: String): Boolean {
        if (courseId in GlobalData.courses) {
            if (courseId in currentCourses) {
                GlobalData.courses[courseId]!!.updateAttributes(description = newDescription)
                return true
            }
            throw IllegalStateException("Error: This professor doesn't have this course")
        }
        throw IllegalStateException("Error: Id is not in use")
    }
}

fun main() {
    var a = Admin(0)
    var b = Professor(1, currentCourses = mutableListOf(), officeLocation = "The Moon")
    var c = a.createCourse(0, "Example Title")
    a.assignProfessor(1, 0)
    a.createCourse(1, "Second Example Title")

    //TEST 1: Attempts to change the description of a non-existing course
    try {
        b.updateCourseDescription(9999, "Lorem ipsum")
        println("Test 1 failed: Professor updated description of a non-existing course")
    } catch (e: Exception) {
        println("Test 1 passed: ${e.message}")
    }

    //TEST 2: Attempts to change the description of a course that the professor doesn't have
    try {
        b.updateCourseDescription(1, "Lorem ipsum")
        println("Test 2 failed: Professor updated description of a course that it isn't assigned to")
    } catch (e: Exception) {
        println("Test 2 passed: ${e.message}")
    }

    //TEST 3: Checks that updating a course description works as expected
    try {
        b.updateCourseDescription(0, "Lorem ipsum")
        if (GlobalData.courses[0]!!.description == "Lorem ipsum") {
            println("Test 3 passed: course description changed successfully")
        } else {
            println("Test 3 failed: course description is \"${GlobalData.courses[0]!!.description}\"")
        }
    } catch (e: Exception) {
        println("Test 3 failed: ${e.message}")
    }
}