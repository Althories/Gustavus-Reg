// Branden Hopper

class Professor(userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set", var currentCourses: MutableList<Int>, var officeLocation: String): User(userId) {
    fun updateCourseDescription(courseId: Int, newDescription: String) {
        //courses[courseId].updateAttributes(description: newDescription)
    }
}
