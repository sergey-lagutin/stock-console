package app

import app.actions.{Menu, Auth}

object Start extends App {
  println("Welcome to Stock application.")
  while (true) {
    Auth.currentUser match {
      case Some(user) => Menu.show(user)
      case None => Auth.login()
    }
  }
}
