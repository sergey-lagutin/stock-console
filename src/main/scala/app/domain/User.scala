package app.domain

import app.domain.Role._

case class User(username: String, password: String, role: Role)


