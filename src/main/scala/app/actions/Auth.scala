package app.actions

import app.service.UserService

object Auth {
  private val userService = new UserService

  def currentUser = userService.getCurrentUser

  def login(): Unit = {
    println("To sign in input username and password (try customer/customer or admin/admin).")
    val username = readString("username:")
    val password = readString("password:")
    userService.login(username, password) match {
      case Some(user) =>
        println(s"Hello ${user.username} [${user.role}]")
      case None => println("Incorrect username or password")
    }
  }

  def logout(): Unit = {
    userService.logout()
    println("Logged out")
  }
}
