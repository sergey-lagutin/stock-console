package app

import app.actions.{Menu, Auth}

object Start extends App {
  println("Welcome to Stock application.")
  while (true) {
    Auth.currentUser match {
      case Some(user) => Menu.showMenu(user)
      case None => Auth.login()
    }
  }
}
