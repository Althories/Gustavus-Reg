// Hayden Jones
class Degree(val degreeId: Int) {

    var name: String = ""
    var requiredCourses = mutableListOf<Int>()
    var isMajor: Boolean = false

    init {

        if (this.degreeId in GlobalData.degrees.keys){
            throw IllegalStateException("Guys, you can't do that. That degree already exists")
        }
        GlobalData.degrees[this.degreeId] = this
    }
}