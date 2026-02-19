import kotlin.arrayOf
import kotlin.collections.mutableListOf

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

    fun updateAtributes(
        title : String = "NA",
        credits: Int = -1,
        days: Array<Boolean> = arrayOf<Boolean>(false,false,false,false,false),
        time : Int = -1,
        departmentId : Int = -1,
        description : String ="NA",
        capacity : Int = 0,
        location : String  = "NA") : Boolean {
        if(title != "NA"){
            this.title = title
        }
        return true
    }
    fun getCapacity() : Int{
        return this.capacity
    }
    fun getStudents() : MutableList<Int> {
        return this.students
    }
    
}


fun main() {
    val calc2 = Course(1)
    calc2.updateAtributes(title = "Calc2")
    print(calc2.title)
}