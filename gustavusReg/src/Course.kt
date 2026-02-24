import kotlin.arrayOf
import kotlin.collections.mutableListOf

//Course Class, BMCP Solhjem
class Course(val courseId : Int){
    var title : String = "NA"
    var credits: Int = 4
    var days =  arrayOf<Boolean>(false,false,false,false,false)
    var time : Int = -1
    var departmentId : Int = -1
    var description : String ="NA"
    var students  =  mutableListOf<Int>()
    var capacity : Int = -1
    var location : String  = "NA"

    init {
        // Initializer block runs when an instance is created
        if (this.courseId in GlobalData.courses.keys){
            throw IllegalStateException("Guys, you can't do that")
        }
        GlobalData.courses[this.courseId] = this
    }

    fun updateAttributes(
        title : String = "NA",
        credits: Int = -1,
        days: Array<Boolean> = arrayOf<Boolean>(false,false,false,false,false),
        time : Int = -1,
        departmentId : Int = -1,
        description : String ="NA",
        capacity : Int = 0,
        location : String  = "NA") : Boolean {

        if(title != "NA"){ this.title = title }
        if(credits != -1){ this.credits = credits }
        if(!days.contentEquals(arrayOf<Boolean>(false,false,false,false,false))){ this.days = days }
        if(time != -1){ this.time = time }
        if(departmentId != -1){ this.departmentId = departmentId }
        if(description != "NA"){ this.description = description }
        if(capacity != -1){ this.capacity = capacity }
        if(location != "NA"){ this.location = location }
        return true
    }
    fun _getCapacity() : Int{
        return this.capacity
    }
    fun _getStudents() : MutableList<Int> {
        return this.students
    }
    override fun toString() : String{
        return this.title + " in " + this.location + "\n\t" + this.description
    }
}


fun main() {
    val calc2 = Course(1)
    calc2.updateAttributes(title = "Calc2")

    try {
        Course(1)
        println("Test 1 Failed: Repeat Class")
    } catch (e: Exception) {
        println("Test 1 Passed: ${e.message} error")
    }

    try {
        println(calc2)

        calc2.updateAttributes(title = "Calc2 But different", credits =1,
        days = arrayOf<Boolean>(true,false,true,false,true),
        time = 1000,
        departmentId  = 1,
        description  ="This is the course description",
        capacity = 20,
        location = "Olin 321")
        println(calc2)
        println("Test 2 Passed: Attributes Updated")
    } catch (e: Exception){
        println("Test 2 Failed: ${e.message}")
    }


    if (calc2.toString() == "Calc2 But different in Olin 321\n\tThis is the course description") {
        println("Test 3 Passed: Attributes Match the String")
    } else{
    println("Test 3 Failed")
    }


}