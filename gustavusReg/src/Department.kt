// Hayden Jones
class Department(val departmentId: Int){
    private var name: String = ""
    private var courses = mutableListOf<Int>()
    private var degrees = mutableListOf<Int>()
    private var professors = mutableListOf<Int>()
    init {
        //init is called when a new instance of the class is created.
        if (this.departmentId in GlobalData.departments.keys){
            throw IllegalStateException("Guys, you can't do that")
        }
        GlobalData.departments[this.departmentId] = this
    }
}