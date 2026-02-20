// Hayden Jones
class Degree(val degreeId: Int) {

    private var name: String = ""
    private var requiredCourses = mutableListOf<Int>()
    private var isMajor: Boolean = false

    init {

        if (this.degreeId in GlobalData.degrees.keys){
            throw IllegalStateException("Guys, you can't do that. That degree already exists")
        }
        GlobalData.degrees[this.degreeId] = this
    }
}