package app.service

import app.domain.{Role, User}

private[service] object Users {
  val users = List(
    User("customer", "customer", Role.CUSTOMER),
    User("admin", "admin", Role.ADMIN)
  )
}

class UserHolder(val currentUser: Option[User]) {
  private[service] def findUser(username: String, password: String): Option[User] =
    Users.users.find(u => u.username == username && u.password == password)

  def login(username: String, password: String): UserHolder =
    new UserHolder(findUser(username, password))

  def logout: UserHolder = new UserHolder(None)
}
