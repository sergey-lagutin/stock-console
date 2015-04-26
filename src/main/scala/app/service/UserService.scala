package app.service

import app.domain.{Role, User}

private[service] object Users {
  val users = List(
    User("customer", "customer", Role.CUSTOMER),
    User("admin", "admin", Role.ADMIN)
  )
}

private[service] object CurrentUser {
  private var user: Option[User] = None

  def setUser(u: Option[User]): Unit = {
    user = u
  }

  def currentUser = user
}

class UserService {

  private[service] def findUser(username: String, password: String): Option[User] =
    Users.users.find(u => u.username == username && u.password == password)

  def getCurrentUser = CurrentUser.currentUser

  def login(username: String, password: String): Option[User] = {
    val user = findUser(username, password)
    CurrentUser.setUser(user)
    user
  }

  def logout(): Unit = {
    CurrentUser.setUser(None)
  }
}
