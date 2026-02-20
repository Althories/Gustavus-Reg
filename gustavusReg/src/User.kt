//User Class: Nikolas Kopek

@file:Suppress("SpellCheckingInspection")
//open keyword means other classes can inherit from User
//Technically there is no reason for you to create a new instance of user, and instead creating instances of its subclasses.
open class User(val userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set") {
    private var accessing: Boolean = false //Whether user is currently accessing service

    //init called when a new instance of the class is created
    init {
        if (this.userId in GlobalData.users.keys){
            throw IllegalStateException("Error: UserId already exists in this system")
        }
        GlobalData.users[this.userId] = this //Adds new instance of User to GlobalData map of Users
    }

    private fun login(password: String): Boolean {
        this.accessing = password == this.password  //User accessing service if password matches
        return password == this.password            //Returns whether password matches
    }

    private fun logout() {
        this.accessing = false
    }

    //Let user update name, email, password
    public fun updateProfile(name: String = this.name, email: String = this.email, password: String = this.password) {
        this.name = name
        this.email = email
        this.password = password
    }

    //Allows User to see account details. Additional implementation not included in our UML diagram.
    override fun toString(): String {
        return "Account Details:\nName: $name\nEmail: $email\nPassword: $password"
    }

    //private fun createUser() {
    //This function was originally in our UML diagram. We found this function had no reason to exist here
    //as its functionality was handled in Main.kt, and we did not need users creating other users.}
}

fun main() {
    val user1 = User(0)
    user1.updateProfile(
        name = "Johnma",
        email = "Johnma_Jay@gustavus.edu",
        //password = "johnmaR00les"
    )
    println(user1.toString())
    user1.updateProfile(name = "Junesma", password = "junesmaR00les")
    println(user1.toString())
    //var user2 = User(0) Throws error defined in init
}
//TODO whatever Unit testing applies to User