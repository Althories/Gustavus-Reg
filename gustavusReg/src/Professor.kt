// Branden Hopper

class Professor(userId: Int, var currentCourses: MutableList<Int>, var officeLocation: String): User(userId) {
    fun updateCourseDescription(courseId: Int, newDescription: String) {
        //courses[courseId].updateAttributes(description: newDescription)
    }
}
