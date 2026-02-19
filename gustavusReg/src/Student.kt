//Cooper Ptacek

class Student(userId: Int) : User(userId) {

    //Initializers
    var gpa: Float = 0.0f
    var year: Int = 1
    var graduationDate: Int = -1
    var classes = mutableListOf<Int>()
    var degrees = mutableListOf<Int>()

    //Course related functions
    fun enrollCourse(courseId: Int): Boolean {
        val currentCourse = GlobalData.courses.find { it.courseId == courseId }
            ?: throw IllegalArgumentException("Course not found")       //?: apparently called Elvis Operator, used since line 14 could return null
        if (classes.contains(courseId))                                 // effecively if course is null throw error, else use course
            throw IllegalStateException("Already enrolled in this course dummy")
        // check capacity
        if (currentCourse.students.size >= currentCourse.capacity)
            throw IllegalStateException("U too slow, course is full.")
        // updates
        classes.add(courseId)
        currentCourse.students.add(this.userId)
        return true
    }

    fun dropCourse(courseId: Int): Boolean {
        val currentCourse = GlobalData.courses.find { it.courseId == courseId }
            ?: throw IllegalArgumentException("Course not found")
        if (!classes.contains(courseId))
            throw IllegalStateException("U are not even in this course")
        classes.remove(courseId)
        currentCourse.students.remove(this.userId)
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

fun main() {

    val course1 = Course(101)
    course1.updateAttributes(title = "Software", capacity = 2)

    val course2 = Course(102)
    course2.updateAttributes(title = "Stats", capacity = 1)

    GlobalData.courses.add(course1)
    GlobalData.courses.add(course2)

    val student = Student(1)

    // TEST 1: Enroll Successfully
    try {
        student.enrollCourse(101)
        println("Test 1 Passed: Enrolled successfully")
    } catch (e: Exception) {
        println("Test 1 Failed: ${e.message}")
    }

    // TEST 2: Enroll Twice (should throw error)
    try {
        student.enrollCourse(101)
        println("Test 2 Failed: Duplicate enrollment allowed")
    } catch (e: IllegalStateException) {
        println("Test 2 Passed: ${e.message}")
    }

    // TEST 3: Capacity Limit
    val student2 = Student(2)
    try {
        student2.enrollCourse(102)  // fills capacity
        val student3 = Student(3)
        student3.enrollCourse(102)  // should fail
        println("Test 3 Failed: Capacity exceeded")
    } catch (e: IllegalStateException) {
        println("Test 3 Passed: ${e.message}")
    }

    // TEST 4: Drop Successfully
    try {
        student.dropCourse(101)
        println("Test 4 Passed: Dropped successfully")
    } catch (e: Exception) {
        println("Test 4 Failed: ${e.message}")
    }

    // TEST 5: Drop Course Not Enrolled
    try {
        student.dropCourse(101)
        println("Test 5 Failed: Dropped course not enrolled in")
    } catch (e: IllegalStateException) {
        println("Test 5 Passed: ${e.message}")
    }

    // TEST 6: Declare Degree
    if (student.declareDegree(1)) {
        println("Test 6 Passed: Degree declared")
    } else {
        println("Test 6 Failed")
    }

    // TEST 7: Declare Same Degree Twice
    if (!student.declareDegree(1)) {
        println("Test 7 Passed: Duplicate degree prevented")
    } else {
        println("Test 7 Failed")
    }

    // TEST 8: Enroll in non-existent Course
    try {
        student.enrollCourse(103)
        println("Test 8 Failed: Enrolled successfully")
    } catch (e: Exception) {
        println("Test 8 Passed: ${e.message}")
    }
}