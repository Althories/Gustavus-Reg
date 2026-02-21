//User Class: Nikolas Kopek
//Functions in User are public instead of private in the UML diagram. We realized that these functions needed to be called by a user

@file:Suppress("SpellCheckingInspection")
//open keyword means other classes can inherit from User
//Technically there is no reason for you to create a new instance of user, and instead creating instances of its subclasses.
open class User(val userId: Int, private var name: String = "Not Set", private var email: String = "Not Set", private var password: String = "Not Set") {
    private var accessing: Boolean = false //Whether user is currently accessing service

    //init called when a new instance of the class is created
    init {
        if (this.userId in GlobalData.users.keys){
            throw IllegalStateException("Error: UserId already exists")
        }
        GlobalData.users[this.userId] = this //Adds new instance of User to GlobalData map of Users
    }

    public fun login(password: String): Boolean {
        if (this.accessing) {   //If true, user is already logged into service
            throw IllegalStateException("Error: User already logged in")
        }
        this.accessing = password == this.password     //Accessing set true if password matches or not password is set
        return this.accessing                          //If accessing is true, user password was correct or not set yet
    }

    public fun logout() {
        if (!this.accessing) {
            throw IllegalStateException("Error: Cannot logout, user not logged in")
        }
        this.accessing = false
    }

    //Let user update name, email, password
    public fun updateProfile(name: String = this.name, email: String = this.email, password: String = this.password) {
        if (!this.accessing) {
            throw IllegalStateException("Error: Cannot update profile, user not logged in")
        }
        this.name = name
        this.email = email
        this.password = password
    }

    //Allows User to see account details. Additional implementation not included in our UML diagram.
    override fun toString(): String {
        if (!this.accessing) {
            throw IllegalStateException("Error: Cannot view account details, user not logged in")
        }
        return "Account Details:\nName: $name\nEmail: $email\nPassword: $password"
    }

    //private fun createUser() {
    //This function was originally in our UML diagram. We found this function had no reason to exist here
    //as its functionality was handled in Main.kt, and we did not need users creating other users.}
}

fun main() {

    //Used for tests 1-4
    val user1 = User(0, "Usera", "usera@gustavus.edu", "gust1esw1llsh1ne")

    val user2 = User(1, "Userchamp", "thechamp@gustavus.edu", "nah1dw1n")

    //Used for test 6
    val user3 = User(2, "Userguy", "theuserguy@gustavus.edu")

    //Playground block
    user2.login("nah1dw1n")
    println(user2.toString())

    //TEST 1: User attempts login when already accessing
    try {
        user1.login("gust1esw1llsh1ne")
        user1.login("gust1esw1llsh1ne")
        println("Test 1 Failed: User logged in when already accessing")
    } catch (e: Exception) {
        println("Test 1 Passed: ${e.message}")
    }

    //TEST 2: User attempts logout when not accessing
    try {
        user1.logout() //Interacts with TEST 1.
        user1.logout()
        println("Test 2 Failed: User logged out when not accessing")
    } catch (e: Exception) {
        println("Test 2 Passed: ${e.message}")
    }

    //TEST 3: User attempts to update profile information when not accessing
    try {
        user1.updateProfile("Not_Usera", "not_usera@gac.edu", "gustiesWONTshine")
        println("Test 3 Failed: User updated profile when not accessing")
    } catch (e: Exception) {
        println("Test 3 Passed: ${e.message}")
    }

    //TEST 4: User attempts to fetch account details without being logged in
    try {
        user1.toString()
        println("Test 4 Failed: User got account details when not accessing")
    } catch (e: Exception) {
        println("Test 4 Passed: ${e.message}")
    }

    //TEST 5: Attempt to create instance of user with duplicate UserId
    try {
        val user4 = User(0, "Useristus", "useristus@gustavus.edu", "gustiesw1llsh1ne")
        println("Test 5 Failed: User with duplicate UserId created successfully")
    } catch (e: Exception) {
        println("Test 5 Passed: ${e.message}")
    }

    //TEST 6: User can log in with default password (Not Set)
    try {
        user3.login("Not Set")
        user3.toString()
        println("Test 6 Passed: User logged in with default password")
    } catch (e: Exception) {
        println("Test 6 Failed: User could not login with default password")
    }
}