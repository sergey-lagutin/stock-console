package app

import app.actions._
import app.service.{Stock, Users}

object Start extends App {
  println("Welcome to Stock application.")

  eventLoop(new Users(), new Stock(Map("item1" -> 10)))

  def eventLoop(holder: Users, stock: Stock): Unit = {
    def readAction: Action = holder.currentUser
      .map(Menu.show(_, stock))
      .getOrElse(AuthActions.login())

    readAction match {
      case Logout => eventLoop(holder.logout, stock)
      case Login(username, password) => eventLoop(holder.login(username, password), stock)
      case AddItem(item, amount) => eventLoop(holder, stock.add(item, amount))
      case BuyItem(item, amount) => eventLoop(holder, stock.buy(item, amount))
      case UnknownOperation =>
        println("Unknown operation")
        eventLoop(holder, stock)
    }
  }
}
