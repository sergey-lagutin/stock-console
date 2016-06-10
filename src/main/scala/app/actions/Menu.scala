package app.actions

import app.domain.User
import app.domain.Role._

object Menu {

  private case class MenuItem(name: String, action: Action, key: String)

  private type Action = () => Unit

  private val customerActions: List[(String, Action)] = List(
    ("Buy item", Stock.buy _))

  private val adminActions: List[(String, Action)] = List(
    ("Add item", Stock.add _))

  def show(user: User): Unit = {
    val userActions = user.role match {
      case CUSTOMER => customerActions
      case ADMIN => customerActions ++ adminActions
    }

    Stock.showAll()

    val menu =
      (for (((name, action), i) <- userActions.zipWithIndex)
      yield MenuItem(name, action, (i + 1).toString)) :+ MenuItem("Logout", Auth.logout, "0")

    println("Available actions:")
    menu.foreach(
      item => println(s"${item.key}. ${item.name}")
    )
    val action = readString("Input menu number:")
    menu.find(_.key == action) match {
      case None => println("Unknown menu number")
      case Some(item) => item.action()
    }
  }

}
