//User Class: Nikolas Kopek
open class User(val userId: Int) { //open means other classes can inherit from User
    private var name: String = ""
    private var email: String = ""
    private var password: String = ""
    private var accessing: Boolean = false //Whether user is currently accessing service

    init {
        //TODO hook up to data global
        //init is called when a new instance of the class is created.
    }

    private fun login(password: String): Boolean {
        this.accessing = password == this.password  //User accessing service if password matches
        return password == this.password            //Returns whether password matches
    }

    private fun logout() {
        this.accessing = false
    }   //User no longer accessing service upon logout

    private fun updateProfile(name: String = this.name, email: String = this.email) {
        this.name = name
        this.email = email
    }
    //private fun createUser() {
    //This function was originally in our UML diagram. We found this function to be unnecessary
    //as its functionality was handled in Main.kt and we did not need users creating other users.
    //}
}

//TODO whatever Unit testing applies to these
//TODO make it so new users are added to the global list of Users in Main (add init)