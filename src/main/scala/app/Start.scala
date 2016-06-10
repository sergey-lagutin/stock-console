package app

import app.actions._
import app.service.{Stock, UserHolder}

object Start extends App {
  println("Welcome to Stock application.")

  eventLoop(new UserHolder(None), new Stock(Map("item1" -> 10)))

  def eventLoop(holder: UserHolder, stock: Stock): Unit = {
    def readAction: Action = holder.currentUser match {
      case None => AuthActions.login()
      case Some(user) => Menu.show(user, stock)
    }

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
