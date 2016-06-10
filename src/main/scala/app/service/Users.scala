package app.service

import app.domain.{Role, User}

class Users(val currentUser: Option[User], users: List[User]) {

  def this() {
    this(None, List(
      User("customer", "customer", Role.CUSTOMER),
      User("admin", "admin", Role.ADMIN)))
  }

  private[service] def findUser(username: String, password: String): Option[User] =
    users.find(u => u.username == username && u.password == password)

  def login(username: String, password: String): Users =
    new Users(findUser(username, password), users)

  def logout: Users = new Users(None, users)
}
