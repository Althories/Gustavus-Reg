// Branden Hopper

class Professor(userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set", var currentCourses: MutableList<Int>, var officeLocation: String): User(userId) {

    // Updates a course's description.
    // Course must exist and be in this professor's current courses.
    // Returns true on success and false otherwise.
    fun updateCourseDescription(courseId: Int, newDescription: String): Boolean {
        if (courseId in GlobalData.courses && courseId in currentCourses) {
            GlobalData.courses[courseId]!!.updateAttributes(description = newDescription)
            return true
        }
        return false
    }
}
