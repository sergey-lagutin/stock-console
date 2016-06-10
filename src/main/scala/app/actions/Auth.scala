package app.actions

object Auth {

  def login(): Action = {
    println("To sign in input username and password (try customer/customer or admin/admin).")
    val username = readString("username:")
    val password = readString("password:")
    Login(username, password)
  }

  def logout(): Action = Logout
}
