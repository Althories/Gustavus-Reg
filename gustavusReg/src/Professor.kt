// Branden Hopper (Reedspun)

class Professor(userId: Int, var currentCourses: MutableSet<Int>, var officeLocation: String): User(userId) {
    fun updateCourseDescription(courseId: Int, newDescription: String) {
        GlobalData.courses[courseId]!!.updateAttributes(description = newDescription)
    }
}
