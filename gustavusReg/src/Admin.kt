// Branden Hopper (Reedspun)

import kotlin.arrayOf

class Admin(userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set"): User(userId) {

    // Create a new instance of course
    // Returns the course if course was successfully created, throws error otherwise
    fun createCourse(courseId: Int, courseTitle: String): Course {
        if (courseId !in GlobalData.courses)
        {
            val course = Course(courseId)
            course.updateAttributes(title = courseTitle)
            return course
        }
        throw IllegalStateException("Error: The selected courseId is already in use")
    }

    // Assign a course to a professor
    // Returns true if course was successfully assigned, throws error otherwise
    fun assignProfessor(professorId: Int, courseId: Int): Boolean {
        if (professorId in GlobalData.users) {  //If the supplied professor ID exists in User ID list
            if (GlobalData.users[professorId] is Professor) { //If the supplied ID is for an instance of Professor
                if (courseId !in (GlobalData.users[professorId] as Professor).currentCourses) {
                    //If the supplied course ID is not in the professor's list of current courses
                    (GlobalData.users[professorId] as Professor).currentCourses.add(courseId)
                    return true
                }
                throw IllegalStateException("Error: This courseId is already assigned to this professor")
            }
            throw IllegalStateException("Error: This user is not a professor")
        }
        throw IllegalStateException("Error: professorId is not in use")
    }

	// Change the amount of credits that a course has
    // Returns true if course was successfully updated, throws error otherwise
    fun updateCourseCredits(courseId: Int, newCredits: Int): Boolean {
        if (newCredits > -1) {
            if (courseId in GlobalData.courses) {
                GlobalData.courses[courseId]!!.updateAttributes(credits = newCredits)
                return true
            }
            throw IllegalStateException("Error: This Id is not in use")
        }
        throw IllegalStateException("Error: newCredits must be non-negative")
    }

	// Change the days that a course meets on
    // Returns true if course was successfully updated, throws error otherwise
    fun updateCourseDays(courseId: Int, newDays: Array<Boolean>): Boolean {
        if (newDays.size == 5) {
            if (courseId in GlobalData.courses) {
                Course(courseId).updateAttributes(days = newDays)
                return true
            }
            throw IllegalStateException("Error: This Id is not in use")
        }
        throw IllegalStateException("Error: newDays array size must be 5")
    }

	// Change the times that a course meets on
    // Returns true if course was successfully updated, throws error otherwise
    fun updateCourseTime(courseId: Int, newTime: Int): Boolean {
        if (newTime > -1) {
            if (courseId in GlobalData.courses) {
                Course(courseId).updateAttributes(time = newTime)
                return true
            }
            throw IllegalStateException("Error: This Id is not in use")
        }
        throw IllegalStateException("Error: newTime must be a non-negative value")
    }

	// Change the year of a student
    // Returns true if student was successfully updated, throws error otherwise
    fun updateYear(studentId: Int, newYear: Int): Boolean {
        if (newYear > 0) {
            if (studentId in GlobalData.users) {
                if (GlobalData.users[studentId] is Student) {
                    (GlobalData.users[studentId] as Student).year = newYear
                    return true
                }
                throw IllegalStateException("Error: This user is not a student")
            }
            throw IllegalStateException("Error: This Id is not in use")
        }
        throw IllegalStateException("Error: newYear must be a positive value")
    }

	// Change the GPA of a student
    // Returns true if student was successfully updated, throws error otherwise
	fun updateGPA(studentId: Int, newGPA: Float): Boolean {
        if (newGPA >= 0.0f) {
            if (studentId in GlobalData.users) {
                if (GlobalData.users[studentId] is Student) {
                    (GlobalData.users[studentId] as Student).gpa = newGPA
                    return true
                }
                throw IllegalStateException("Error: This user is not a student")
            }
            throw IllegalStateException("Error: This Id is not in use")
        }
        throw IllegalStateException("Error: newGPA must be non-negative")
	}

	// Assign a student to a course
    // Returns true if student and course were successfully updated, throws error otherwise
    fun registerStudent(studentId: Int, courseId: Int): Boolean {
        if (studentId in GlobalData.users) {
            if (GlobalData.users[studentId] is Student) {
                if (GlobalData.courses[courseId] is Course) {
                    if (courseId !in (GlobalData.users[studentId] as Student).classes) {
                        if (studentId !in GlobalData.courses[courseId]!!.students) {
                            (GlobalData.users[studentId] as Student).classes.add(courseId)
                            GlobalData.courses[courseId]!!.students.add(studentId)
                            // Not using Student.kt's built-in enroll since admin can ignore capacity
                            return true
                        }
                        throw IllegalStateException("Error: This student is already assigned to this course")
                    }
                    throw IllegalStateException("Error: This course is already assigned to this student")
                }
                throw IllegalStateException("Error: courseId is not in use")
            }
            throw IllegalStateException("Error: This user is not a student")
        }
        throw IllegalStateException("Error: studentId is not in use")
    }

	// Remove a student from a course
    // Returns true if student was successfully removed from the course, throws error otherwise
    fun removeStudent(studentId: Int, courseId: Int): Boolean {
        if (studentId in GlobalData.users) {
            if (GlobalData.users[studentId] is Student) {
                if (GlobalData.courses[courseId] is Course) {
                    if (courseId in (GlobalData.users[studentId] as Student).classes) {
                        if (studentId in GlobalData.courses[courseId]!!.students) {
                            (GlobalData.users[studentId] as Student).classes.remove(courseId)
                            GlobalData.courses[courseId]!!.students.remove(studentId)
                            return true
                            // Generally, this code should work as long as the if statement is true,
                            // but just in case it somehow fails on one end and not the other,
                            // this function returns two booleans in a list.
                        }
                        throw IllegalStateException("Error: This student is not assigned to this course")
                    }
                    throw IllegalStateException("Error: This course is not assigned to this student")
                }
                throw IllegalStateException("Error: courseId is not in use")
            }
            throw IllegalStateException("Error: This user is not a student")
        }
        throw IllegalStateException("Error: studentId is not in use")
    }

	// Change the courses required for a major
    // Takes a major (degree) ID and a list of courseId
    // and replaces the old list with the new list.
    // Returns true if degree is successfully updated, throws error otherwise
    fun updateRequirements(degreeId: Int, courseList: Array<Int>): Boolean {
        if (degreeId in GlobalData.degrees) {
            for (id in courseList) {
                if (id !in GlobalData.courses) {
                    throw IllegalStateException("Error: course Id $id in courseList is not in use")
                }
            }
            GlobalData.degrees[degreeId]!!.requiredCourses = courseList.toMutableList()
            return true
        }
        throw IllegalStateException("Error: This Id is not in use")
    }

	// Remove a course from the database
    // Returns true if course is successfully removed, throws error otherwise.
    fun removeCourse(courseId: Int): Boolean {
        if (courseId in GlobalData.courses) {
            // First we should remove all students from the course
            for (studentId in GlobalData.courses[courseId]!!.students) {
                (GlobalData.users[studentId] as Student).dropCourse(courseId)
            }
            // Then we should remove the course from its professors
            // Unfortunately there's no easy way to do this since courses don't keep track of their professors,
            // so I will just search every user until we find all professors.
            for (userId in GlobalData.users.keys) {
                if (GlobalData.users[userId] is Professor) {
                    if (courseId in (GlobalData.users[userId] as Professor).currentCourses) {
                        (GlobalData.users[userId] as Professor).currentCourses.remove(courseId)
                    }
                }
            }
            // Then we should remove the course from any degrees
            for (degreeId in GlobalData.degrees.keys) {
                GlobalData.degrees[degreeId]!!.requiredCourses.remove(courseId)
            }
            // Then from its department
            for (departmentId in GlobalData.departments.keys) {
                GlobalData.departments[departmentId]!!.courses.remove(courseId)
            }
            // Then finally we can remove it from the list of courses
            GlobalData.courses.remove(courseId)
            // Might wanna do memory cleanup but idk how lol
            return true
        }
        throw IllegalStateException("Error: This Id is not in use")
    }
}

fun main() {

    //Example instances used for unit testing
    val a1 = Admin(0, "John Admin", "jadmin@gleeble.gov", "1234")
    val a2 = Admin(1, "Jane Admin", "jadmin2@gleeble.gov", "4321")
    val p1 = Professor(2, "Louis Yu", "lyu@gustavus.edu", "louis'sPasswordDONOTSTEAL", mutableListOf(), "Olin 999")
    val p2 = Professor(3, "Guarionex Salivia", "gsalivia@gustavus.edu", "GuarioPrism", mutableListOf(), "Olin 1234")
    val s1 = Student(4, "Gus Davis", "gus@gustavus.edu", "adolphus")
    val s2 = Student(5)
    val s3 = Student(6)
    val s4 = Student(7)
    val s5 = Student(8)
    val s6 = Student(999)
    val c1: Course = a1.createCourse(0, "MCS401")
    val c2: Course = a2.createCourse(1, "FTS001")
    a2.assignProfessor(p1.userId, 0)
    a1.assignProfessor(p2.userId, 0)
    a2.registerStudent(s1.userId, c1.courseId)
    a1.registerStudent(s1.userId, c2.courseId)

    //TEST 1: Admin attempts to create course with a courseId already in use
    try {
        val testCourse1: Course = a1.createCourse(2, "NEW101")
        val testCourse2: Course = a1.createCourse(2, "NEW102")
        println("Test 1 Failed: Admin created course with duplicate courseId")
    } catch (e: Exception) {
        println("Test 1 Passed: ${e.message}")
    }

    //TEST 2: Admin attempts to call assignProfessor with an ID not in use (9999)
    try {
        a1.assignProfessor(9999, c1.courseId)
        println("Test 2 Failed: Admin assigned professor to course without supplying an existing professorId")
    } catch (e: Exception) {
        println("Test 2 Passed: ${e.message}")
    }

    //TEST 3: Admin attempts to assign non-professor user to course
    try {
        a1.assignProfessor(5, c1.courseId)
        println("Test 3 Failed: Admin assigned non-professor user to course")
    } catch (e: Exception) {
        println("Test 3 Passed: ${e.message}")
    }

    //TEST 4: Admin attempts to assign professor to course already assigned to them in currentCourses
    try {
        a1.assignProfessor(2, c1.courseId)
        println("Test 4 Failed: Admin assigned professor to course they are already assigned to")
    } catch (e: Exception) {
        println("Test 4 Passed: ${e.message}")
    }
}