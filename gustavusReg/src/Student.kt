//Cooper Ptacek

class Student(userId: Int) : User(userId) {

    //Initializers
    var gpa: Float = 0.0f
    var year: Int = 1
    var graduationDate: Int = 2030
    var classes = mutableListOf<Int>()
    var degrees = mutableListOf<Int>()

    //Course related functions
    fun enrollCourse(courseId: Int): Boolean {
        if (classes.contains(courseId))
            throw IllegalStateException("Already enrolled in this course dummy")
        // check capacity
        if (Course.students.size >= Course.capacity)
            throw IllegalStateException("U too slow, course is full.")
        // updates
        classes.add(courseId)
        Course.students.add(this.userId)
        return true
    }

    fun dropCourse(courseId: Int): Boolean {
        if (!classes.contains(courseId))
            throw IllegalStateException("U are not even in this course")
        classes.remove(courseId)
        Course.students.remove(this.userId)
        return true
    }

    //Degree Related functions
    fun declareDegree(degreeId: Int): Boolean {
        if (degrees.contains(degreeId))
            return false
        degrees.add(degreeId)
        return true
    }
}