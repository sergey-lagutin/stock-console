package app.service

import app.domain.{Role, User}
import org.scalatest.FunSuite

class UserServiceTest extends FunSuite {
  test("findUser: user not found") {
    new UserService {
      assert(None === findUser("user", "user"))
    }
  }

  test("findUser") {
    new UserService {
      val expected = Some(User("admin", "admin", Role.ADMIN))
      assert(expected === findUser("admin", "admin"))
    }
  }
}
