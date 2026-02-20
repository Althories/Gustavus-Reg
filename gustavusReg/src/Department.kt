// Hayden Jones
class Department(val departmentId: Int){
    var name: String = ""
    var courses = mutableListOf<Int>()
    var degrees = mutableListOf<Int>()
    var professors = mutableListOf<Int>()
    init {
        //init is called when a new instance of the class is created.
        if (this.departmentId in GlobalData.departments.keys){
            throw IllegalStateException("Guys, you can't do that")
        }
        GlobalData.departments[this.departmentId] = this
    }
}