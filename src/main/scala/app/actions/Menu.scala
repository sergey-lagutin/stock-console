package app.actions

import app.domain.User
import app.domain.Role._
import app.service.Stock

object Menu {

  private case class MenuItem(name: String, action: ActionSource, key: String)

  private type ActionSource = () => Action

  private val customerActions: List[(String, ActionSource)] = List(
    ("Buy item", StockActions.buy _))

  private val adminActions: List[(String, ActionSource)] = List(
    ("Add item", StockActions.add _))

  def show(user: User, stock: Stock): Action = {
    val userActions = user.role match {
      case CUSTOMER => customerActions
      case ADMIN => customerActions ++ adminActions
    }

    stock.showAll()

    val menu =
      (for (((name, action), i) <- userActions.zipWithIndex)
        yield MenuItem(name, action, (i + 1).toString)) :+ MenuItem("Logout", AuthActions.logout, "0")

    println("Available actions:")
    menu.foreach(
      item => println(s"${item.key}. ${item.name}")
    )
    val action = readString("Input menu number:")
    menu.find(_.key == action)
      .map(_.action())
      .getOrElse(UnknownOperation)
  }

}
