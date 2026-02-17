open class User(val userID: Int) {
    private var name: String = ""
    private var email: String = ""
    private var password: String = ""
    private var accessing: Boolean = false

    private fun login(password: String): Boolean {
        this.accessing = password == this.password
        return password == this.password
    }

    private fun logout() {this.accessing = false}

    private fun updateProfile(name: String = this.name, email: String = this.email) {
        this.name = name
        this.email = email
    }

}