 class Course(val courseId : Int){
        var title : String = "NA"
        var credits: int = 4
        var days : bool[] = [false,false,false,false,false]
        var time : int = -1
        var departmentId : int = -1
        var description : str ="NA"
        var students  : int[] = []
        var capacity : int = -1
        var location : str  = "NA"

        fun updateAtributes(
            title : String = "NA"
            credits: int = -1
            days : bool[] = [false,false,false,false,false]
            time : int = -1
            departmentId : int = -1
            description : str ="NA"
            capacity : int = 0
            location : str  = "NA"){
            if(title != "NA"){
                this.title = title
            }
        }
    }


fun main() {
    val calc2 = Course(1)
    calc2.updateAtributes(title = "Calc2")
    print(calc2.title)
}