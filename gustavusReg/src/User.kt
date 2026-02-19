//User Class: Nikolas Kopek
open class User(val userID: Int) { //open means other classes can inherit from User
    private var name: String = ""
    private var email: String = ""
    private var password: String = ""
    private var accessing: Boolean = false //Whether user is currently accessing service

    //Login function
    private fun login(password: String): Boolean {
        this.accessing = password == this.password  //User accessing service if password matches
        return password == this.password            //Returns whether password matches
    }

    //Logout function
    private fun logout() {this.accessing = false}   //User no longer accessing service upon logout

    private fun updateProfile(name: String = this.name, email: String = this.email) {
        this.name = name
        this.email = email
    }

}

//TODO whatever Unit testing applies to these
//TODO implement createUser