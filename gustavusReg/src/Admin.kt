// Branden Hopper (Reedspun)

import kotlin.arrayOf
class Admin(userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set"): User(userId) {

    // Create a new instance of course
    // Returns true if course was successfully created, false otherwise
    private fun createCourse(courseId: Int, courseTitle: String): Boolean {
        if (courseId !in GlobalData.courses)
        {
            val course = Course(courseId)
            course.updateAttributes(title = courseTitle)
            return true
        }
        return false
    }

    // Assign a course to a professor
    // Returns true if course was successfully assigned, false otherwise
    private fun assignProfessor(professorId: Int, courseId: Int): Boolean {
        if (professorId in GlobalData.users
            && GlobalData.users[professorId] is Professor
            && courseId !in (GlobalData.users[professorId] as Professor).currentCourses)
        {
            (GlobalData.users[professorId] as Professor).currentCourses.add(courseId)
            return true
        }
        return false
    }

	// Change the amount of credits that a course has
    // Returns true if course was successfully updated, false otherwise
    private fun updateCourseCredits(courseId: Int, newCredits: Int): Boolean {
        if (newCredits > -1
            && courseId in GlobalData.courses)
        {
            GlobalData.courses[courseId]!!.updateAttributes(credits = newCredits)
            return true
        }
        return false
    }

	// Change the days that a course meets on
    // Returns true if course was successfully updated, false otherwise
    private fun updateCourseDays(courseId: Int, newDays: Array<Boolean>): Boolean {
        if (newDays.size == 5
            && courseId in GlobalData.courses)
        {
            Course(courseId).updateAttributes(days = newDays)
            return true
        }
        return false
    }

	// Change the times that a course meets on
    // Returns true if course was successfully updated, false otherwise
    private fun updateCourseTime(courseId: Int, newTime: Int): Boolean {
        if (newTime > -1
            && courseId in GlobalData.courses)
        {
            Course(courseId).updateAttributes(time = newTime)
            return true
        }
        return false
    }

	// Change the year of a student
    // Returns true if student was successfully updated, false otherwise
    private fun updateYear(studentId: Int, newYear: Int): Boolean {
        if (newYear > 0
            && studentId in GlobalData.users
            && GlobalData.users[studentId] is Student)
        {
            (GlobalData.users[studentId] as Student).year = newYear
            return true
        }
        return false
    }

	// Change the GPA of a student
    // Returns true if student was successfully updated, false otherwise
	private fun updateGPA(studentId: Int, newGPA: Float): Boolean {
        if (newGPA >= 0.0f
            && studentId in GlobalData.users
            && GlobalData.users[studentId] is Student)
        {
            (GlobalData.users[studentId] as Student).gpa = newGPA
            return true
        }
        return false
	}

	// Assign a student to a course
    // Returns true if student and course were successfully updated, false otherwise
    private fun registerStudent(studentId: Int, courseId: Int): Boolean {
        if (studentId in GlobalData.users
            && GlobalData.users[studentId] is Student
            && GlobalData.courses[courseId] is Course
            && courseId !in (GlobalData.users[studentId] as Student).classes
            && studentId !in GlobalData.courses[courseId]!!.students) {

            (GlobalData.users[studentId] as Student).classes.add(courseId)
            GlobalData.courses[courseId]!!.students.add(studentId)
            // Not using Student.kt's built-in enroll since admin can ignore capacity
            return true
        }
        return false
    }

	// Remove a student from a course
    // Returns a list of two booleans
    // The first element of the list is true if the course was successfully removed from the student.
    // The second, if the student was successfully removed from the course.
    private fun removeStudent(studentId: Int, courseId: Int): Array<Boolean> {
        if (studentId in GlobalData.users
            && GlobalData.users[studentId] is Student
            && GlobalData.courses[courseId] is Course
            && courseId in (GlobalData.users[studentId] as Student).classes
            && studentId in GlobalData.courses[courseId]!!.students)
        {
            val a: Boolean = (GlobalData.users[studentId] as Student).classes.remove(courseId)
            val b: Boolean = GlobalData.courses[courseId]!!.students.remove(studentId)
            return arrayOf(a, b)
            // Generally, this code should work as long as the if statement is true,
            // but just in case it somehow fails on one end and not the other,
            // this function returns two booleans in a list.
        }
        return arrayOf(false, false)
    }

	// Change the courses required for a major
    // Takes a major (degree) ID and a list of courseId
    // and replaces the old list with the new list.
    // Returns true if degree is successfully updated, false otherwise.
    private fun updateRequirements(degreeId: Int, courseList: Array<Int>): Boolean {
        // Everything in Degree.kt is private so I can't modify or even read it.
        // Once it gets updated to not be entirely private I will redo this function.
        return false
    }

	// Remove a course from the database
    // Returns true if course is successfully removed, false otherwise.
    private fun removeCourse(courseId: Int): Boolean {
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
            //for (degreeId in GlobalData.degrees) {
                // Since degrees are private this is TODO
            //}
            // Then from its department
            //for (departmentId in GlobalData.departments) {
                // Same deal
            //}
            // Then finally we can remove it from the list of courses
            GlobalData.courses.remove(courseId)
            // Might wanna do memory cleanup but idk how lol
            return true
        }
        return false
    }
}